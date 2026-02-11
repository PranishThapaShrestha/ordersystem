package com.buddha.ordersystem.entity;

import com.buddha.ordersystem.auth.entity.RefreshToken;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    @NonNull
    private String userName;

    @Column(name = "email")
    @Email(message = "Go with correct email format")
    @NotBlank
    private String email;

    @Column(name = "passwords")
    @NonNull
    private String password;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private boolean accountNonlocked = true;

    private int failedAttempts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL
            , orphanRemoval = true
            , mappedBy = "user"
            , fetch = FetchType.LAZY)
    private List<RefreshToken> refreshTokens;


}
