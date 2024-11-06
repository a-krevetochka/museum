package com.mgtu.museum.entity;

import com.mgtu.museum.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "users")
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

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "role", nullable = false, length = 16)
    private UserRole role;

    @Column(name = "secret", nullable = false, length = 256)
    private String secret;

    @ColumnDefault("('user')")
    @Column(name = "username", length = 128)
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.toString();
            }
        });
    }

    @Override
    public String getPassword() {
        return secret;
    }
}
