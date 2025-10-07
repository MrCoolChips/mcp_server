package com.mcp.server.domain.client.dto.public_api.response;

import java.util.List;

/**
 * Represents the response from the OpenAI Chat API.
 * <p>
 * Contains metadata about the response and a list of {@link Choice} objects
 * representing the different generated responses from the model.
 * </p>
 *
 * @param id      the unique identifier of the response
 * @param object  the type of the object returned (usually "chat.completion")
 * @param created the timestamp of creation in epoch seconds
 * @param model   the model used to generate this response
 * @param choices the list of generated responses as {@link Choice} objects
 */
public record OpenAiChatResponse(
        String id,
        String object,
        long created,
        String model,
        List<Choice> choices
) {}