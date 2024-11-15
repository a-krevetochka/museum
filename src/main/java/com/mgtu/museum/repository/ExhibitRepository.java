package com.mgtu.museum.repository;

import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.entity.Exhibit;
import com.mgtu.museum.mapper.ExhibitMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitRepository {
    JdbcTemplate jdbcTemplate;

    public void save(Exhibit exhibit) {
        String sql = """
                Insert into exhibit(name, packaging_type, storage_shelf_id) values (?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName(),
                exhibit.getPackagingType().name().toLowerCase(),
                exhibit.getStorageShelfId());
    }

    public void update(Exhibit exhibit) {
        String sql = """
                Update exhibit set name = ?, packaging_type = ?, storage_shelf_id = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName(),
                exhibit.getPackagingType().name().toLowerCase(),
                exhibit.getStorageShelfId(),
                exhibit.getId());
    }

    public Exhibit findById(Integer id) {
        String sql = """
                select 
                id as exhibit_id, 
                name as exhibit_name,
                packaging_type as exhibit_packaging_type,
                storage_shelf_id as exhibit_storage_shelf_id
                from exhibit where id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), id).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Экспоната с id " + id + " не существует"));
    }
}
