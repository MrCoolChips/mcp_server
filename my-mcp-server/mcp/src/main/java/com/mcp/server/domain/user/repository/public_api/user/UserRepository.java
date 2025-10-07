package com.mcp.server.domain.user.repository.public_api.user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mcp.server.domain.user.entity.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByMail(String mail);
}


