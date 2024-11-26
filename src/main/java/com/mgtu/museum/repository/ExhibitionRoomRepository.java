package com.mgtu.museum.repository;

import com.mgtu.museum.entity.ExhibitionRoom;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitionRoomRepository {
    private final JdbcTemplate jdbcTemplate;
    public Integer getExhibitionIdByRoomId(Integer roomId) {
        String sql = "select exhibition_id from exhibition_room where room_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roomId);
    }

    public void save(ExhibitionRoom build) {
        String sql = """
                Insert into exhibition_room (room_id, exhibition_id) values (?, ?)
                """.trim();
        jdbcTemplate.update(sql, build.getRoomId(), build.getExhibitionId());
    }
}
