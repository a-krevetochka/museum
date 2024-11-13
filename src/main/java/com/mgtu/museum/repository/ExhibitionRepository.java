package com.mgtu.museum.repository;

import com.mgtu.museum.controller.ExhibitionController.dto.CreateExhibitionDto;
import com.mgtu.museum.controller.ExhibitionController.dto.UpdateExhibitionDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
@AllArgsConstructor
public class ExhibitionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public void save(CreateExhibitionDto dto) {
        String sql = """
                INSERT INTO exhibitions(name, description, date_from, date_to) values (?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate());
    }

    public void update(UpdateExhibitionDto dto) {
        String sql = """
                UPDATE exhibitions SET name=?, description=?, date_from=?, date_to=? where id=?
                """.trim();
        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getId());
    }
}
