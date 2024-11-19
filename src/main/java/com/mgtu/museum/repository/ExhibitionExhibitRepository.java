package com.mgtu.museum.repository;

import com.mgtu.museum.entity.ExhibitionExhibit;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitionExhibitRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(ExhibitionExhibit exhibition) {
        String sql = """
                Insert into exhibition_exhibit (exhibit_id,shelf_id,exhibition_id,description_id) values (?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                exhibition.getExhibitId(),
                exhibition.getShelfId(),
                exhibition.getExhibitionId(),
                exhibition.getDescriptionId());
    }

}
