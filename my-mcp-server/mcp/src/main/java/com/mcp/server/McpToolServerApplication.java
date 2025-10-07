package com.mcp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class McpToolServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpToolServerApplication.class, args);
    }
}
