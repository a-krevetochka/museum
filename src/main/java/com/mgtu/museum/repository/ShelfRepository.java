package com.mgtu.museum.repository;

import com.mgtu.museum.controller.StorageUnitController.Request.UpdateShelfRequest;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.entity.Shelf;
import com.mgtu.museum.mapper.ExhibitionMapper;
import com.mgtu.museum.mapper.ShelfMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    public void save(Shelf shelf) {
        String sql = """
                INSERT INTO shelf (number, shelving_id, description) VALUES (?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql, shelf.getNumber(), shelf.getShelvingId(), shelf.getDescription());
    }

    public Integer getExhibitionIdByShelfId(Integer shelfId) {
        String sql = """
                select exhibition.id from shelf
                JOIN shelving on shelving.id = shelf.shelving_id
                JOIN room on room.id = shelf.room_id
                JOIN exhibition on exhibition.id = shelf.exhibition_id
                where shelf_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Integer.class, shelfId);
    }
    public List<Shelf> findAllByShelvingId(Integer shelvingId) {
        String sql = """
                select
                id as shelf_id,
                number as shelf_number,
                shelving_id shelf_shelving_id
                description
                from shelf
                where shelving_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ShelfMapper(), shelvingId);
    }

    public void updateDescription(UpdateShelfRequest dto) {
        String sql = """
                UPDATE shelf set description = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql, dto.getDescription(), dto.getShelfId());
    }

    public void delete(Integer shelfId) {
        String sql = """
                delete from shelf where id = ?
                """.trim();
        jdbcTemplate.update(sql, shelfId);
    }
}
