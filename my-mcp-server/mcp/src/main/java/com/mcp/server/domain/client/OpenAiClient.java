package com.mcp.server.domain.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.server.common.constant.OpenAIConstants;
import com.mcp.server.common.exception.common.enums.ExceptionError;
import com.mcp.server.common.exception.core.InternalServerErrorException;
import com.mcp.server.domain.client.config.OpenAiProperties;
import com.mcp.server.domain.client.dto.public_api.request.OpenAiChatRequest;
import com.mcp.server.domain.client.dto.public_api.response.OpenAiChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Client for interacting with the OpenAI Chat API.
 * <p>
 * Sends prompts to OpenAI and parses the response as a JSON object.
 * Provides methods for specifying a custom model or using the default one
 * defined in {@link OpenAiProperties}.
 * </p>
 *
 * <p>
 * Handles error cases including empty responses or HTTP 4xx/5xx errors, and
 * wraps them as {@link InternalServerErrorException}.
 * </p>
 *
 * @see OpenAiChatRequest
 * @see OpenAiChatResponse
 * @see OpenAiProperties
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiClient {

    private static final String CHAT_COMPLETIONS = "";

    private final RestClient openAiRestClient;
    private final OpenAiProperties props;
    private final ObjectMapper mapper;

    /**
     * Sends a prompt to OpenAI and parses the response into the specified type.
     *
     * @param prompt       the prompt to send to OpenAI
     * @param responseType the type to deserialize the JSON response into
     * @param <T>          the type of the response object
     * @return the parsed response object
     * @throws InternalServerErrorException if the OpenAI API call fails or returns invalid content
     */
    public <T> T chatJson(String prompt, Class<T> responseType) {
        return chatJson(prompt, null, responseType);
    }

    /**
     * Sends a prompt to OpenAI using a specified model (or the default model) and parses the response.
     *
     * @param prompt        the prompt to send to OpenAI
     * @param modelOverride optional model name to override the default
     * @param responseType  the type to deserialize the JSON response into
     * @param <T>           the type of the response object
     * @return the parsed response object
     * @throws InternalServerErrorException if the OpenAI API call fails, response is empty, or content is invalid
     */
    public <T> T chatJson(String prompt, String modelOverride, Class<T> responseType) {
        final String model = (modelOverride != null && !modelOverride.isBlank())
                ? modelOverride
                : (props.model() != null && !props.model().isBlank() ? props.model() : "gpt-4o-mini");

        final OpenAiChatRequest requestBody = new OpenAiChatRequest(
                model,
                List.of(
                        new OpenAiChatRequest.Message("system", OpenAIConstants.SYSTEM_PROMPT),
                        new OpenAiChatRequest.Message("user", prompt)
                ),
                OpenAiChatRequest.ResponseFormat.jsonObject()
        );

        try {
            final OpenAiChatResponse resp = openAiRestClient
                    .post()
                    .uri(CHAT_COMPLETIONS)
                    .body(requestBody)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        final String body = res.getBody() != null
                                ? StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8)
                                : null;
                        log.error("4xx OpenAI error. status={}, body={}", res.getStatusCode(), body);
                        throw new InternalServerErrorException(ExceptionError.EXTERNAL_API_CALL_FAILED);
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        final String body = res.getBody() != null
                                ? StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8)
                                : null;
                        log.error("5xx OpenAI error. status={}, body={}", res.getStatusCode(), body);
                        throw new InternalServerErrorException(ExceptionError.EXTERNAL_API_CALL_FAILED);
                    })
                    .body(OpenAiChatResponse.class);

            if (resp == null || resp.choices() == null || resp.choices().isEmpty()) {
                log.error("OpenAI returned empty/invalid response: {}", resp);
                throw new InternalServerErrorException(ExceptionError.EXTERNAL_API_CALL_FAILED);
            }

            final var first = resp.choices().get(0);
            if (first.message() == null || first.message().content() == null || first.message().content().isBlank()) {
                log.error("OpenAI response has no content. choice={}", first);
                throw new InternalServerErrorException(ExceptionError.EXTERNAL_API_CALL_FAILED);
            }

            final String content = first.message().content();
            return mapper.readValue(content, responseType);

        } catch (InternalServerErrorException e) {
            throw e; 
        } catch (Exception e) {
            log.error("OpenAI chat call error: {}", e.getMessage(), e);
            throw new InternalServerErrorException(ExceptionError.EXTERNAL_API_CALL_FAILED);
        }
    }
}
