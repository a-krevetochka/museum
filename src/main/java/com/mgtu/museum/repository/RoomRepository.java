package com.mgtu.museum.repository;

import com.mgtu.museum.entity.Room;
import com.mgtu.museum.mapper.RoomMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

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

    public List<Room> getRoomsByExibitionId(Integer exhibitionId) {
        String sql = """
                select r.* from exhibition_room er
                                join room r on er.room_id = r.id
                                where er.exhibition_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new RoomMapper(), exhibitionId);
    }

    public List<Room> getAll() {
        String sql = """
                select * from room
                """.trim();
        return jdbcTemplate.query(sql, new RoomMapper());
    }

    public void delete(Integer id) {
        String sql = """
                delete from room where id = ?
                """.trim();
        jdbcTemplate.update(sql, id);
    }
}
