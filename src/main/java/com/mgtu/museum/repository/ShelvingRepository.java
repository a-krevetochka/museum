package com.mgtu.museum.repository;

import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.entity.Shelving;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class ShelvingRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(Shelving shelving) {
        String sql = """
                INSERT INTO shelving (number, room_id) values (?, ?)
                """.trim();
        jdbcTemplate.update(sql, shelving.getNumber(), shelving.getRoomId());
    }

    public List<GetShelvingsResponse> findAllByRoom(@NonNull Integer roomId) {
        String sql = """
                select id, number from shelving where room_id=?
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetShelvingsResponse>() {

            @Override
            public GetShelvingsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetShelvingsResponse.builder()
                        .id(rs.getInt("id"))
                        .number(rs.getInt("number"))
                        .build();
            }
        }, roomId);
    }

    public void updateRoom(@NonNull Integer shelvingId, @NonNull Integer roomId) {
        String sql = """
                Update shelving set room_id=? where id=?
                """.trim();
        jdbcTemplate.update(sql, roomId, shelvingId);
    }

    public void deleteById(Integer shelvingId) {
        String sql = """
                delete from shelving where id=?
                """.trim();
        jdbcTemplate.update(sql, shelvingId);
    }
}
