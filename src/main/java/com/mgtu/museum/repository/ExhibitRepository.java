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
                Insert into exhibit(name) values (?)
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName());
    }

    public void update(Exhibit exhibit) {
        String sql = """
                Update exhibit set name = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName(),
                exhibit.getId());
    }

    public Exhibit findById(Integer id) {
        String sql = """
                select\s
                id as exhibit_id,\s
                name as exhibit_name
                from exhibit where id = ?
               \s""".trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), id).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Экспоната с id " + id + " не существует"));
    }

}
