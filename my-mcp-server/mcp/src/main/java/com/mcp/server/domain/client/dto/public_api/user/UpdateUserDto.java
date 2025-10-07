package com.mcp.server.domain.client.dto.public_api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for updating an existing user.
 * <p>
 * Fields in this DTO are optional but validated if provided. Jakarta Bean
 * Validation annotations ensure that provided values meet required constraints.
 * </p>
 *
 * @param name the user's name (optional, must have at least 1 character if provided)
 * @param mail the user's email address (optional, must be a valid email if provided)
 * @param age  the user's age (optional, must be between 0 and 150 if provided)
 */
public record UpdateUserDto(
        @Size(min = 1) String name,
        @Email String mail,
        @Min(0) @Max(150) Integer age
) {}
