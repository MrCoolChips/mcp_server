package com.mcp.server.domain.user.service.public_api.user.core.impl;

import com.mcp.server.domain.user.dto.public_api.CreateUserDto;
import com.mcp.server.domain.user.dto.public_api.UserResponseDto;
import com.mcp.server.domain.user.dto.public_api.UserUpdateDto;
import com.mcp.server.domain.user.entity.user.User;
import com.mcp.server.domain.user.repository.public_api.user.UserRepository;
import com.mcp.server.domain.user.service.public_api.user.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link UserService} for managing users.
 * <p>
 * This service provides CRUD operations for user entities using {@link UserRepository}.
 * All results are returned as {@link UserResponseDto} to ensure consistent API responses.
 * </p>
 *
 * <p>
 * Methods are transactional, with read-only transactions for retrieval operations
 * and read-write transactions for create, update, and delete operations.
 * </p>
 *
 * @see UserService
 * @see UserRepository
 * @see UserResponseDto
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Converts a {@link User} entity to a {@link UserResponseDto}.
     *
     * @param u the user entity
     * @return the corresponding DTO
     */
    private static UserResponseDto toDto(User u) {
        return new UserResponseDto(u.getId(), u.getName(), u.getMail(), u.getAge());
    }

    /**
     * Creates a new user.
     *
     * @param dto the user creation data
     * @return the created user as {@link UserResponseDto}
     */
    @Transactional
    @Override
    public UserResponseDto create(CreateUserDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setMail(dto.mail());
        user.setAge(dto.age());

        User saved = userRepository.save(user);
        return toDto(saved);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users as {@link UserResponseDto}
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserServiceImpl::toDto)
                .toList();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the UUID of the user
     * @return an {@link Optional} containing the user if found, empty otherwise
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponseDto> getById(UUID id) {
        return userRepository.findById(id).map(UserServiceImpl::toDto);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param mail the email of the user
     * @return an {@link Optional} containing the user if found, empty otherwise
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponseDto> getByMail(String mail) {
        return userRepository.findByMail(mail).map(UserServiceImpl::toDto);
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id      the UUID of the user to update
     * @param payload the user update data
     * @return an {@link Optional} containing the updated user if the user exists,
     *         empty otherwise
     */
    @Transactional
    @Override
    public Optional<UserResponseDto> update(UUID id, UserUpdateDto payload) {
        return userRepository.findById(id).map(user -> {
            if (payload.name() != null) user.setName(payload.name());
            if (payload.mail() != null) user.setMail(payload.mail());
            if (payload.age() != null) user.setAge(payload.age());
            return toDto(userRepository.save(user));
        });
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the UUID of the user to delete
     * @return {@code true} if the user was deleted, {@code false} if the user was not found
     */
    @Transactional
    @Override
    public boolean delete(UUID id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
