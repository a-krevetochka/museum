package com.mgtu.museum.mapper;

import com.mgtu.museum.entity.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setNumber(rs.getInt("number"));
        return room;
    }
}

