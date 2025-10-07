package com.mcp.server.domain.user.entity.user;


import com.mcp.server.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder 

public class User extends BaseEntity {    
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "mail", length = 255, nullable = false)
    private String mail;
    @Column(name = "age", nullable = false)
    private int age;    
}
