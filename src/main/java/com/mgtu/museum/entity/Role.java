package com.mgtu.museum.entity;

import com.mgtu.museum.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private UserRole role;

    @Override
    public String getAuthority() {
        return role.toString();
    }

}
