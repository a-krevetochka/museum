package com.mgtu.museum.repository;

import com.mgtu.museum.controller.ExhibitionController.Request.CreateExhibitionRequest;
import com.mgtu.museum.controller.ExhibitionController.Request.UpdateExhibitionRequest;
import com.mgtu.museum.entity.Exhibition;
import com.mgtu.museum.mapper.ExhibitionMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
@AllArgsConstructor
public class ExhibitionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public void save(CreateExhibitionRequest dto) {
        String sql = """
                INSERT INTO exhibition(name, description, date_from, date_to) values (?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate());
    }

    public void update(UpdateExhibitionRequest dto) {
        String sql = """
                UPDATE exhibition SET name=?, description=?, date_from=?, date_to=? where id=?
                """.trim();
        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getId());
    }

    public List<Exhibition> getAll() {
        String sql = """
                select id          as exhibition_id,
                       name        as exhibition_name,
                       description as exhibition_description,
                       date_from   as exhibition_date_from,
                       date_to     as exhibition_date_to
                from exhibition
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitionMapper());
    }

    public Exhibition getById(int id) {
        String sql = """
                select id          as exhibition_id,
                       name        as exhibition_name,
                       description as exhibition_description,
                       date_from   as exhibition_date_from,
                       date_to     as exhibition_date_to
                from exhibition where id=?
                """.trim();
        return jdbcTemplate.queryForObject(sql, new ExhibitionMapper(), id);
    }

    public void delete(Integer id) {
        String sql = """
                delete from exhibition where id=?
                """.trim();
        jdbcTemplate.update(sql, id);
    }
}
