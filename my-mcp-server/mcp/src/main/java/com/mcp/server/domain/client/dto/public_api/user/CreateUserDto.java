package com.mcp.server.domain.client.dto.public_api.user;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for creating a new user.
 * <p>
 * This DTO is validated using Jakarta Bean Validation annotations to ensure
 * that all required fields are properly provided.
 * </p>
 *
 * @param name the name of the user (must not be blank)
 * @param mail the user's email address (must be a valid email and not blank)
 * @param age  the user's age (must be between 0 and 150)
 */
public record CreateUserDto(
        @NotBlank String name,
        @Email @NotBlank String mail,
        @Min(0) @Max(150) Integer age
) {}
