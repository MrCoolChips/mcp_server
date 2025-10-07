package com.mcp.server.domain.user.dto.public_api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data Transfer Object for updating an existing user via the public API.
 * <p>
 * Fields are optional; only the provided fields will be updated.
 * </p>
 *
 * <ul>
 *     <li>{@code name} - the new name of the user (optional)</li>
 *     <li>{@code mail} - the new email address of the user (optional)</li>
 *     <li>{@code age} - the new age of the user (optional)</li>
 * </ul>
 *
 * <p>
 * {@link JsonInclude(JsonInclude.Include.NON_NULL)} ensures that only non-null fields
 * are serialized to JSON when sending update requests.
 * </p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserUpdateDto(
        String name,
        String mail,
        Integer age
) {}
