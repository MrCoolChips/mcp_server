package com.mcp.server.common.constant;

/**
 * A utility class that defines constants related to OpenAI operations.
 * <p>
 * This class is not intended to be instantiated. It provides a central location
 * for constants such as system prompts used in OpenAI API interactions.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     String prompt = OpenAIConstants.SYSTEM_PROMPT;
 * </pre>
 * </p>
 * 
 * @version 1.0
 */
public final class OpenAIConstants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * Throws {@link UnsupportedOperationException} if called.
     */
    private OpenAIConstants() {
        throw new UnsupportedOperationException("Constant class cannot be instantiated");
    }

    /**
     * System prompt for OpenAI API interactions.
     * <p>
     * This prompt instructs the assistant to return a valid JSON object for
     * user CRUD operations. The JSON object should contain the fields:
     * <ul>
     *     <li>{@code operation}: create, get, update, delete</li>
     *     <li>{@code data}: the user data (empty object for "get all")</li>
     * </ul>
     * <p>
     * Important rules:
     * <ul>
     *     <li>Output only a valid JSON object.</li>
     *     <li>No explanations or markdown should be included.</li>
     *     <li>Do not use 'email', use 'mail' only.</li>
     * </ul>
     */
    public static final String SYSTEM_PROMPT =
            "You are an assistant that returns a valid JSON object for user CRUD operations. " +
            "The JSON object has fields: 'operation' (create, get, update, delete), and 'data' (the user data). " +
            "If user wants to get all, set 'operation' to 'get' and data to {}. " +
            "Only output a valid JSON object, no explanations, no markdown, nothing else, Do NOT use 'email', use ONLY 'mail'.";
}
