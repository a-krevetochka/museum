package com.mgtu.museum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "middle_name", length = 64)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 64)
    private String lastName;

    @JoinColumn(name = "role")
    private Role role;


    @ColumnDefault("('user')")
    @Column(name = "username", length = 128)
    private String username;

    @Size(max = 256)
    @NotNull
    @Column(name = "secret", nullable = false, length = 1024)
    private String secret;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return secret;
    }
}

