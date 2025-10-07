package com.mcp.server.domain.user.controller.public_api;

import com.mcp.server.domain.user.dto.public_api.CreateUserDto;
import com.mcp.server.domain.user.dto.public_api.UserResponseDto;
import com.mcp.server.domain.user.dto.public_api.UserUpdateDto;
import com.mcp.server.domain.user.service.public_api.user.core.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing users in the public API.
 * <p>
 * Provides endpoints to create, read, update, and delete users.
 * All responses use {@link UserResponseDto} to represent user data.
 * </p>
 *
 * @see UserService
 * @see CreateUserDto
 * @see UserUpdateDto
 * @see UserResponseDto
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user.
     *
     * @param dto the user creation data
     * @return the created user wrapped in a {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid CreateUserDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the UUID of the user
     * @return the user if found, or 404 Not Found if the user does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param mail the email of the user
     * @return the user if found, or 404 Not Found if the user does not exist
     */
    @GetMapping("/by-mail")
    public ResponseEntity<UserResponseDto> getUserByMail(@RequestParam String mail) {
        return userService.getByMail(mail)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id      the UUID of the user to update
     * @param payload the user update data
     * @return the updated user if found, or 404 Not Found if the user does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDto payload) {
        return userService.update(id, payload)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the UUID of the user to delete
     * @return 204 No Content if deletion was successful, or 404 Not Found if the user does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
