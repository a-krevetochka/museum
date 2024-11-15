package com.mgtu.museum.repository;

import com.mgtu.museum.entity.Shelf;
import com.mgtu.museum.mapper.ShelfMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ShelfRepository {
    private final JdbcTemplate jdbcTemplate;

    public Shelf findById(@NonNull Integer id) {
        String sql = """
                select * from shelf where id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ShelfMapper(), id).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Полки не c id  = " + id + " существует"));
    }
}
