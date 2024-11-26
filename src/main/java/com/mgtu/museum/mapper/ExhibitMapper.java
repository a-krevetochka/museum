package com.mgtu.museum.mapper;

import com.mgtu.museum.entity.Exhibit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitMapper implements RowMapper<Exhibit> {
    @Override
    public Exhibit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exhibit exhibit = new Exhibit();
        exhibit.setId(rs.getInt("exhibit_id"));
        exhibit.setName(rs.getString("exhibit_name"));
        return exhibit;
    }
}
