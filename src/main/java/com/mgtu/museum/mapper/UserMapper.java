package com.mgtu.museum.mapper;

import com.mgtu.museum.Enum.UserRole;
import com.mgtu.museum.entity.Role;
import com.mgtu.museum.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUpdatedAt(rs.getDate("updated_at"));
        user.setDeletedAt(rs.getDate("deleted_at"));
        user.setCreatedAt(rs.getDate("created_at"));
        user.setRole(new Role(UserRole.valueOf(rs.getString("role"))));
        user.setSecret(rs.getString("secret"));
        user.setUsername(rs.getString("username"));
        return user;
    }
}
