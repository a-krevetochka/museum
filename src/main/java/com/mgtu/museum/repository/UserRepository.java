package com.mgtu.museum.repository;

import com.mgtu.museum.entity.User;
import com.mgtu.museum.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;
    private final ModelMapper mapper;

    public User findByUsername(String username) {
        String sql = """
                SELECT
                id,
                name,
                middle_name,
                last_name,
                updated_at,
                deleted_at,
                created_at,
                role,
                secret,
                username
                FROM users
                WHERE username = ?
                """.trim();
        return jdbcTemplate.query(sql, new UserMapper(), username).stream().findFirst().orElse(null);
    }

    public void save(User user) {
        String sql = """
                INSERT INTO users (name, middle_name, last_name, role, secret, username) values (?, ?, ?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                user.getName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRole().toString(),
                user.getSecret(),
                user.getUsername());
    }
}
