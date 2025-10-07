package com.mcp.server.domain.client.service.public_api.user.core.impl;

import com.mcp.server.domain.client.dto.public_api.user.CreateUserDto;
import com.mcp.server.domain.client.dto.public_api.user.UpdateUserDto;
import com.mcp.server.domain.client.service.public_api.user.core.ClientService;
import com.mcp.server.domain.user.entity.user.User;
import com.mcp.server.domain.user.repository.public_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link ClientService} for managing {@link User} entities.
 * <p>
 * Uses {@link UserRepository} to perform CRUD operations.
 * Transactions are managed with Spring's {@link Transactional} annotation.
 * </p>
 * 
 * @see ClientService
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;

    @Override
    public User create(CreateUserDto dto) {
        User u = new User();
        u.setName(dto.name());
        u.setMail(dto.mail());
        u.setAge(dto.age());
        return userRepository.save(u);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(UUID id, UpdateUserDto dto) {
        User u = userRepository.findById(id).orElse(null);
        if (u == null) return null;

        if (dto.name() != null && !dto.name().isBlank()) u.setName(dto.name());
        if (dto.mail() != null && !dto.mail().isBlank()) u.setMail(dto.mail());
        if (dto.age()  != null)                          u.setAge(dto.age());

        return userRepository.save(u);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
