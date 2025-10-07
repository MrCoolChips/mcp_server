package com.mcp.server.domain.client.controller.public_api;

import com.mcp.server.domain.client.OpenAiClient;
import com.mcp.server.domain.client.dto.public_api.user.CreateUserDto;
import com.mcp.server.domain.client.dto.public_api.user.UpdateUserDto;
import com.mcp.server.domain.client.service.public_api.user.core.ClientService;
import com.mcp.server.domain.user.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.mcp.server.domain.client.service.public_api.user.core.NlpCrudService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller that manages user CRUD operations and NLP-based user operations.
 * <p>
 * Exposes endpoints under the {@code /admin} path and allows cross-origin requests
 * from any origin.
 * </p>
 * 
 * <p>
 * Endpoints include:
 * <ul>
 *     <li>{@code POST /admin} - Create a new user</li>
 *     <li>{@code GET /admin} - Retrieve all users</li>
 *     <li>{@code GET /admin/{id}} - Retrieve a user by ID</li>
 *     <li>{@code PUT /admin/{id}} - Update a user by ID</li>
 *     <li>{@code DELETE /admin/{id}} - Delete a user by ID</li>
 *     <li>{@code POST /admin/nlp} - Perform NLP-based CRUD operations</li>
 * </ul>
 * </p>
 * 
 * @version 1.0
 * @see ClientService
 * @see NlpCrudService
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class McpCompletionToolController {

    private final ClientService userService;
    private final NlpCrudService nlpCrudService;

    /**
     * Creates a new user.
     *
     * @param dto the user data transfer object
     * @return the created {@link User} with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDto dto) {
        User saved = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of {@link User} entities
     */
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the user's UUID
     * @return the {@link User} entity
     * @throws ResponseStatusException if the user is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) {
        User u = userService.getById(id);
        if (u == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return ResponseEntity.ok(u);
    }

    /**
     * Updates a user by their unique ID.
     *
     * @param id  the user's UUID
     * @param dto the updated user data
     * @return the updated {@link User} entity
     * @throws ResponseStatusException if the user is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody @Valid UpdateUserDto dto) {
        User u = userService.update(id, dto);
        if (u == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return ResponseEntity.ok(u);
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the user's UUID
     * @return HTTP 204 (No Content) if deleted successfully
     * @throws ResponseStatusException if the user is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        boolean deleted = userService.deleteById(id);
        if (!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint to process NLP-based CRUD operations for users via OpenAI.
     * <p>
     * This method listens to HTTP POST requests at the path <code>/admin/nlp</code>.
     * A JSON payload containing the prompt must be sent in the request body.
     * </p>
     *
     * <p>
     * <strong>Why POST?</strong> POST is used because this endpoint receives data
     * (the NLP prompt) from the client and performs an operation on the server
     * (create, update, delete, or fetch users). Sending the prompt in the request
     * body is standard for POST operations.
     * </p>
     *
     * @param body a map representing the NLP request; it must contain a "prompt" key
     * @return a {@link ResponseEntity} containing the result of the NLP operation
     * @throws org.springframework.web.server.ResponseStatusException if the prompt is missing or invalid
     */
    @PostMapping("/nlp")
    public ResponseEntity<?> nlpCrud(@RequestBody Map<String,Object> body) {
        return nlpCrudService.process(body);
    }
}
