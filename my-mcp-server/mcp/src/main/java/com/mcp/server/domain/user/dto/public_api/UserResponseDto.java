package com.mcp.server.domain.user.dto.public_api;

import java.util.UUID;

/**
 * Data Transfer Object representing a user in API responses.
 * <p>
 * This DTO is returned by the public API endpoints to provide user information.
 * It contains the user's unique identifier, name, email, and age.
 * </p>
 *
 * <ul>
 *     <li>{@code id} - the unique UUID of the user</li>
 *     <li>{@code name} - the full name of the user</li>
 *     <li>{@code mail} - the email address of the user</li>
 *     <li>{@code age} - the age of the user</li>
 * </ul>
 *
 * @see java.util.UUID
 */
public record UserResponseDto(
        UUID id,
        String name,
        String mail,
        Integer age
) {}
