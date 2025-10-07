package com.mcp.server.domain.client.service.public_api.user.core;

import com.mcp.server.domain.client.dto.public_api.user.CreateUserDto;
import com.mcp.server.domain.client.dto.public_api.user.UpdateUserDto;
import com.mcp.server.domain.user.entity.user.User;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing users in the public API.
 * <p>
 * Defines CRUD operations for {@link User} entities.
 * </p>
 * 
 * @see CreateUserDto
 * @see UpdateUserDto
 * @see User
 */
public interface ClientService {

    /**
     * Creates a new user.
     *
     * @param dto the data transfer object containing user information
     * @return the created {@link User} entity
     */
    User create(CreateUserDto dto);

    /**
     * Retrieves all users.
     *
     * @return a list of all {@link User} entities
     */
    List<User> getAll();

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the UUID of the user
     * @return the {@link User} entity, or {@code null} if not found
     */
    User getById(UUID id);

    /**
     * Updates an existing user.
     *
     * @param id  the UUID of the user to update
     * @param dto the data transfer object containing updated information
     * @return the updated {@link User} entity, or {@code null} if not found
     */
    User update(UUID id, UpdateUserDto dto);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the UUID of the user to delete
     * @return {@code true} if deletion was successful, {@code false} otherwise
     */
    boolean deleteById(UUID id);
}
