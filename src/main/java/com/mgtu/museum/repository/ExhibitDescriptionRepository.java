package com.mgtu.museum.repository;

import com.mgtu.museum.entity.ExhibitDescription;
import com.mgtu.museum.mapper.ExhibitDescriptionMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class ExhibitDescriptionRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(ExhibitDescription exhibitDescription) {
        String sql = """
                INSERT INTO exhibit_description(exhibit_id, description) values (?, ?)
                """.trim();
        jdbcTemplate.update(sql, exhibitDescription.getExhibitId(), exhibitDescription.getDescription());
    }

    public List<ExhibitDescription> getAllByExhibitId(Integer id) {
        String sql = """
                select id, description, exhibit_id from exhibit_description where exhibit_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitDescriptionMapper(), id);
    }
}
