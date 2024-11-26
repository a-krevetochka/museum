package com.mgtu.museum.repository;

import com.mgtu.museum.entity.Room;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoomRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(Room build) {
        String sql = """
                Insert into room (number) values (?)
                """.trim();
        jdbcTemplate.update(sql, build.getNumber());
    }

    public void updateRoomId(@NonNull Integer shelvingId, @NonNull Integer roomId) {
        String sql = """
                UPDATE room SET shelving_id=? where room_id=?
                """.trim();
        jdbcTemplate.update(sql, shelvingId, roomId);
    }
}
