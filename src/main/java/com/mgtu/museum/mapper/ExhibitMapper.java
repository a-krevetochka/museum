package com.mgtu.museum.mapper;

import com.mgtu.museum.Enum.PackagingType;
import com.mgtu.museum.entity.Exhibit;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitMapper implements RowMapper<Exhibit> {
    @Override
    public Exhibit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exhibit exhibit = new Exhibit();
        exhibit.setId(rs.getInt("exhibit_id"));
        exhibit.setName(rs.getString("exhibit_name"));
        exhibit.setStorageShelfId(rs.getInt("exhibit_storage_shelf_id"));
        exhibit.setPackagingType(PackagingType.valueOf(rs.getString("exhibit_packaging_type")));
        return exhibit;
    }
}
