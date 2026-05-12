package com.academicsystem.auth_service.entity;

import com.academicsystem.auth_service.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_accounts")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;
}