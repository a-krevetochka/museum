package com.mgtu.museum.mapper;

import com.mgtu.museum.entity.Exhibition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitionMapper implements RowMapper<Exhibition> {
    @Override
    public Exhibition mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exhibition exhibition = new Exhibition();
        exhibition.setId(rs.getInt("exhibition_id"));
        exhibition.setName(rs.getString("exhibition_name"));
        exhibition.setDescription(rs.getString("exhibition_description"));
        exhibition.setDateFrom(rs.getDate("exhibition_date_from"));
        exhibition.setDateTo(rs.getDate("exhibition_date_to"));
        return exhibition;
    }
}
