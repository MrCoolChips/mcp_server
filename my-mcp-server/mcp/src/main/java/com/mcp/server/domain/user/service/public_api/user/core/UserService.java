package com.mcp.server.domain.user.service.public_api.user.core;

import com.mcp.server.domain.user.dto.public_api.CreateUserDto;
import com.mcp.server.domain.user.dto.public_api.UserResponseDto;
import com.mcp.server.domain.user.dto.public_api.UserUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing users in the public API.
 * <p>
 * Defines CRUD operations for user management, including creation, retrieval,
 * update, and deletion. All methods operate on {@link UserResponseDto} for output
 * and use DTOs for input.
 * </p>
 *
 * @see CreateUserDto
 * @see UserUpdateDto
 * @see UserResponseDto
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param dto the user creation data
     * @return the created user as {@link UserResponseDto}
     */
    UserResponseDto create(CreateUserDto dto);

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    List<UserResponseDto> getAll();

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the UUID of the user
     * @return an {@link Optional} containing the user if found, empty otherwise
     */
    Optional<UserResponseDto> getById(UUID id);

    /**
     * Retrieves a user by their email address.
     *
     * @param mail the email of the user
     * @return an {@link Optional} containing the user if found, empty otherwise
     */
    Optional<UserResponseDto> getByMail(String mail);

    /**
     * Updates an existing user by ID.
     *
     * @param id      the UUID of the user to update
     * @param payload the user update data
     * @return an {@link Optional} containing the updated user if the user exists,
     *         or empty if the user does not exist
     */
    Optional<UserResponseDto> update(UUID id, UserUpdateDto payload);

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the UUID of the user to delete
     * @return {@code true} if the user was deleted, {@code false} if the user was not found
     */
    boolean delete(UUID id);
}
