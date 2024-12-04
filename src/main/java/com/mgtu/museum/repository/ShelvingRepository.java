package com.mgtu.museum.repository;

import com.mgtu.museum.controller.QrController.dto.NameDescDto;
import com.mgtu.museum.controller.StorageUnitController.Response.GetRoomResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.entity.Shelving;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@AllArgsConstructor
public class ShelvingRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(Shelving shelving) {
        String sql = """
                INSERT INTO shelving (number, room_id, description) values (?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql, shelving.getNumber(), shelving.getRoomId(), shelving.getDescription());
    }

    public List<GetShelvingsResponse> findAllByRoom(@NonNull Integer roomId) {
        String sql = """
                select id, number, description from shelving where room_id=?
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetShelvingsResponse>() {

            @Override
            public GetShelvingsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetShelvingsResponse.builder()
                        .id(rs.getInt("id"))
                        .number(rs.getInt("number"))
                        .description(rs.getString("description"))
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

    public void updateDescription(@NonNull Integer shelvingId, @NonNull String description) {
        String sql = """
                update shelving set description=? where id=?
                """.trim();
        jdbcTemplate.update(sql, description, shelvingId);
    }

    public Boolean roomHasShelvings(Integer id) {
        String sql = """
                select count(*) > 0 from shelving where room_id=?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    public List<GetShelvingsResponse> findAllFromExhibitions() {
        String sql = """
                select a.id, a.number, a.description
                from shelving a
                         join shelf b on b.shelving_id = a.id
                         join exhibition_exhibit ee on ee.shelf_id = b.id
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetShelvingsResponse>() {

            @Override
            public GetShelvingsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetShelvingsResponse.builder()
                        .id(rs.getInt("id"))
                        .number(rs.getInt("number"))
                        .description(rs.getString("description"))
                        .build();
            }
        });
    }

    public List<NameDescDto> getNamesAndDescriptions(List<Integer> ids) {
        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));

        String sql = String.format("""
                SELECT Concat('полка ', number) as name, description
                FROM shelving
                WHERE id IN (%s)
                """, placeholders);

        List<Object> params = new ArrayList<>(ids);

        return jdbcTemplate.query(sql, new RowMapper<NameDescDto>() {
            @Override
            public NameDescDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return NameDescDto.builder()
                        .name(rs.getString("name"))
                        .desc(rs.getString("description"))
                        .build();
            }
        }, params.toArray());
    }
}
