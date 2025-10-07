package com.mcp.server.domain.client.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for OpenAI API integration.
 * <p>
 * This class binds properties with the prefix {@code openai} from the application
 * configuration (e.g., application.yml or application.properties) and validates
 * them using Jakarta Bean Validation annotations.
 * </p>
 * 
 * <p>
 * Example usage in {@code application.yml}:
 * <pre>
 * openai:
 *   api:
 *     key: YOUR_API_KEY
 *     url: https://api.openai.com
 *   model: gpt-4
 * </pre>
 * </p>
 * 
 * @param api   the API configuration containing key and URL
 * @param model the model name to use for OpenAI requests
 */
@Validated
@ConfigurationProperties(prefix = "openai")
public record OpenAiProperties(
        @Validated Api api,            
        String model   
) {

    /**
     * API configuration for OpenAI integration.
     *
     * @param key the API key (must not be blank)
     * @param url the API base URL (must not be blank)
     */
    public record Api(
            @NotBlank String key,
            @NotBlank String url 
    ) {}
}
