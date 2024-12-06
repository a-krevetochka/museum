package com.mgtu.museum.repository;

import com.mgtu.museum.entity.ExhibitionExhibit;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitionExhibitRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(ExhibitionExhibit exhibition) {
        String sql = """
                Insert into exhibition_exhibit (exhibit_id,shelf_id,exhibition_id,description_id,room_id) values (?, ?, ?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql,
                exhibition.getExhibitId(),
                exhibition.getShelfId(),
                exhibition.getExhibitionId(),
                exhibition.getDescriptionId(),
                exhibition.getRoomId());
    }

    public Boolean isExhibitOnExhibition(@NonNull Integer exhibitId) {
        String sql = """
                select count(*) > 0 from exhibition_exhibit where exhibit_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, exhibitId);
    }

    public Boolean isShelvingEmpty(@NonNull Integer shelvingId) {
        String sql = """
                select count(*) > 0 from exhibition_exhibit ee
                join shelf s on ee.shelf_id = s.id
                where s.shelving_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, shelvingId);
    }

    public Boolean hasExhibits(Integer id) {
        String sql = """
                select count(*) > 0 from exhibition_exhibit where exhibition_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    public Boolean isShelfEmpty(Integer shelfId) {
        String sql = """
                select count(*) > 0 from exhibition_exhibit where shelf_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, shelfId);
    }

    public Boolean roomHasExhibits(Integer id) {
        String sql = """
                select count(*) > 0 from exhibition_exhibit where room_id = ?
                """.trim();
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    public void updateExhibitDescription(Integer descriptionId, @NonNull Integer exhibitionId, @NonNull Integer exhibitId) {
        String sql = """
                update exhibition_exhibit set description_id = ? where exhibit_id = ? and exhibition_id = ?
                """.trim();
        jdbcTemplate.update(sql, descriptionId,exhibitId, exhibitionId);
    }
}
