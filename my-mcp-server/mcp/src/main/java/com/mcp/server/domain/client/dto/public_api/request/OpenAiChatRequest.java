package com.mcp.server.domain.client.dto.public_api.request;

import java.util.List;
import java.util.Map;

/**
 * Represents a request to the OpenAI Chat API.
 * <p>
 * Contains the model to use, a list of messages exchanged in the conversation,
 * and the desired response format.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * OpenAiChatRequest request = new OpenAiChatRequest(
 *     "gpt-4",
 *     List.of(new OpenAiChatRequest.Message("user", "Hello!")),
 *     OpenAiChatRequest.ResponseFormat.jsonObject()
 * );
 * }
 * </pre>
 * </p>
 * 
 * @param model           the name of the OpenAI model to use
 * @param messages        the conversation messages
 * @param response_format the format in which the response should be returned
 */
public record OpenAiChatRequest(
        String model,
        List<Message> messages,
        ResponseFormat response_format
) {

    /**
     * Represents a single message in the conversation.
     *
     * @param role    the role of the message sender (e.g., "user" or "assistant")
     * @param content the content of the message
     */
    public record Message(String role, String content) {}

    /**
     * Represents the desired response format for the OpenAI API.
     *
     * <p>
     * Predefined static factory methods can be used for common formats.
     * </p>
     *
     * @param type the response format type (e.g., "json_object")
     */
    public record ResponseFormat(String type) {
        /**
         * Returns a {@link ResponseFormat} indicating that the response should be a JSON object.
         *
         * @return a {@link ResponseFormat} instance representing JSON object format
         */
        public static ResponseFormat jsonObject() { return new ResponseFormat("json_object"); }
    }
}
