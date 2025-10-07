package com.mcp.server.domain.client.dto.public_api.response;

/**
 * Represents a single message in an OpenAI chat response.
 *
 * @param role    the role of the sender (e.g., "user" or "assistant")
 * @param content the content of the message
 */
public record Message(
        String role,
        String content
) {}
