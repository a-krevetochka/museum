package com.mgtu.museum.repository;

import com.mgtu.museum.entity.Role;
import com.mgtu.museum.entity.User;
import com.mgtu.museum.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public User findByUsername(String username) {
        String sql = """
                SELECT
                id as user_id,
                name as user_name,
                middle_name as user_middle_name,
                last_name as user_last_name,
                role as user_role,
                secret as user_secret,
                username as user_username
                FROM "user"
                WHERE username = ?
                """.trim();
        return jdbcTemplate.query(sql, new UserMapper(), username).stream().findFirst().orElse(null);
    }

    public void save(User user) {
        String sql = """
                INSERT INTO "user" (name, middle_name, last_name, role, secret, username) values (?, ?, ?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                user.getName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRole().getRole().toString(),
                user.getSecret(),
                user.getUsername());
    }

    public void updateUser(User user) {
        String sql = """
                UPDATE "user" set name = ?, middle_name = ?, last_name = ?, role = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql,
                user.getName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRole().getRole().toString(),
                user.getUsername(),
                user.getId());
    }

    public List<User> getAll() {
        String sql = """
                SELECT
                id as user_id,
                name as user_name,
                middle_name as user_middle_name,
                last_name as user_last_name,
                role as user_role,
                secret as user_secret,
                username as user_username
                FROM "user"
                """.trim();
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void delete(Integer id) {
        String sql = """
                DELETE FROM "user" where id = ?
                """.trim();
        jdbcTemplate.update(sql, id);
    }

    public String getRole(String username) {
        String sql = """
                select role from "user" where username = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, String.class, username);
    }

    public void changePassword(User user) {
        String sql = """
                update "user" set secret = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql, user.getSecret(), user.getId());
    }

    public User getById(Integer id) {
        String sql = """
                SELECT
                id as user_id,
                name as user_name,
                middle_name as user_middle_name,
                last_name as user_last_name,
                role as user_role,
                secret as user_secret,
                username as user_username
                FROM "user"
                WHERE id = ?
                """.trim();
        return jdbcTemplate.query(sql, new UserMapper(), id).stream().findFirst().orElse(null);
    }
}
