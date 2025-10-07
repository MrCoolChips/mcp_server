package com.mcp.server.domain.user.dto.public_api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a new user via the public API.
 * <p>
 * Contains the basic information required to create a user:
 * <ul>
 *     <li>{@code name} - the full name of the user, must not be blank</li>
 *     <li>{@code mail} - the email address of the user, must be valid and not blank</li>
 *     <li>{@code age} - the age of the user, must not be null</li>
 * </ul>
 * </p>
 *
 * <p>
 * This DTO is validated using Jakarta Bean Validation annotations.
 * </p>
 *
 * @see jakarta.validation.constraints.NotBlank
 * @see jakarta.validation.constraints.Email
 * @see jakarta.validation.constraints.NotNull
 */
public record CreateUserDto(
        @NotBlank String name,
        @NotBlank @Email String mail,
        @NotNull Integer age
) {}
