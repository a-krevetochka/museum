package com.mgtu.museum.mapper;

import com.mgtu.museum.entity.ExhibitDescription;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitDescriptionMapper implements RowMapper<ExhibitDescription> {

    @Override
    public ExhibitDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExhibitDescription exhibitDescription = new ExhibitDescription();
        exhibitDescription.setId(rs.getInt("id"));
        exhibitDescription.setDescription(rs.getString("description"));
        exhibitDescription.setExhibitId(rs.getInt("exhibit_id"));
        return exhibitDescription;
    }
}
