package com.mcp.server.domain.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Spring configuration class that sets up a {@link RestClient} for OpenAI API interactions.
 * <p>
 * This configuration uses {@link OpenAiProperties} to obtain the API key, base URL, and
 * other necessary settings. The RestClient is configured with default headers for
 * authorization and content type.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * RestClient client = applicationContext.getBean(RestClient.class);
 * }
 * </pre>
 * </p>
 * 
 * @see OpenAiProperties
 */
@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiRestClientConfig {

    /**
     * Creates and configures a {@link RestClient} bean for OpenAI API.
     *
     * @param props the {@link OpenAiProperties} containing API key and URL
     * @return a configured {@link RestClient} instance
     */
    @Bean
    public RestClient openAiRestClient(OpenAiProperties props) {
        return RestClient.builder()
                .baseUrl(props.api().url())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + props.api().key())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
