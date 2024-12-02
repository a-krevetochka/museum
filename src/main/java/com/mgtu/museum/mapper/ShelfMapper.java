package com.mgtu.museum.mapper;

import com.mgtu.museum.entity.Shelf;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelfMapper implements RowMapper<Shelf> {
    @Override
    public Shelf mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shelf shelf = new Shelf();
        shelf.setId(rs.getInt("shelf_id"));
        shelf.setNumber(rs.getInt("shelf_number"));
        shelf.setShelvingId(rs.getInt("shelving_id"));
        shelf.setDescription(rs.getString("description"));
        return shelf;
    }
}
