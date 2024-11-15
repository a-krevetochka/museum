package com.mgtu.museum.mapper;

import com.mgtu.museum.Enum.UserRole;
import com.mgtu.museum.entity.Role;
import com.mgtu.museum.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Base64;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("user_name"));
        user.setMiddleName(rs.getString("user_middle_name"));
        user.setLastName(rs.getString("user_last_name"));
        user.setRole(new Role(
                UserRole.
                        valueOf(
                                rs.getString("user_role")
                        )
        ));
        user.setSecret(rs.getString("user_secret"));
        user.setUsername(rs.getString("user_username"));
        return user;
    }
}
