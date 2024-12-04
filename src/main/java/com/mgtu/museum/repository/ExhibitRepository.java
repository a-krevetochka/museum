package com.mgtu.museum.repository;

import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitWithDescriptionResponse;
import com.mgtu.museum.controller.QrController.request.GenerateExhibitQrRequest;
import com.mgtu.museum.entity.Exhibit;
import com.mgtu.museum.mapper.ExhibitMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

import static java.sql.DriverManager.getConnection;

@Repository
@AllArgsConstructor
public class ExhibitRepository {
    JdbcTemplate jdbcTemplate;

    public void save(Exhibit exhibit) {
        String sql = """
                Insert into exhibit(name) values (?)
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName());
    }

    public void update(Exhibit exhibit) {
        String sql = """
                Update exhibit set name = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql,
                exhibit.getName(),
                exhibit.getId());
    }

    public Exhibit findById(Integer id) {
        String sql = """
                 select\s
                 id as exhibit_id,\s
                 name as exhibit_name
                 from exhibit where id = ?
                \s""".trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), id).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Экспоната с id " + id + " не существует"));
    }

    public List<Exhibit> getAllByExhibitName(String name) {
        String sql = """
                select
                id as exhibit_id,
                name as exhibit_name
                from exhibit where lower(name) like '%%%s%%'
                """.trim();
        sql = String.format(sql, name.toLowerCase());
        return jdbcTemplate.query(sql, new ExhibitMapper());
    }

    public List<Exhibit> findByReceiptNumber(Integer receiptNumber) throws SQLException {
        String sql = """
                 select\s
                 id as exhibit_id,\s
                 name as exhibit_name
                 from exhibit where id = ?
                \s""".trim();
        List<Exhibit> exhibits = jdbcTemplate.query(sql, new ExhibitMapper(), receiptNumber);
        exhibits.addAll(getFrom2DB(receiptNumber));
        return exhibits;
    }

    private List<Exhibit> getFrom2DB(Integer receiptNumber) throws SQLException {
        return null;
//        String url = "jdbc:postgresql://localhost:5432/your_secondary_database_name"; // Replace with your database URL
//        String user = "your_username"; // Replace with your username
//        String password = "your_password"; // Replace with your password
//        List<Exhibit> exhibits = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//            try (Statement statement = connection.createStatement()) {
//                try (Connection dbConnection = DriverManager.getConnection(url, user, password)) {
//                    try (Statement dbStatement = dbConnection.createStatement()) {
//                        ResultSet set = dbStatement.executeQuery(String.format("select" +
//                                "nkp as exhibit_id" +
//                                "fio as exhibit_name" +
//                                "from kp_base where nkp = %s", receiptNumber).trim());
//                        while (set.next()) {
//                            Exhibit exhibit = new Exhibit();// Перебор всех строк в ResultSet
//                            exhibit.setId(set.getInt("exhibit_id")); // Используйте alias, который вы указали в SQL
//                            exhibit.setName(set.getString("exhibit_name")); // Используйте alias, который вы указали в SQL
//                            exhibits.add(exhibit); // Добавление объекта в список
//                        }
//                    } catch (SQLException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }
//        return exhibits;
    }

    public List<Exhibit> getByRoomId(Integer id) {
        String sql = """
                select e.id as exhibit_id, e.name as exhibit_name from exhibition_exhibit ee
                join exhibit e on ee.exhibit_id = ee.id
                where ee.room_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), id);
    }

    public void updateName(@NonNull Integer exhibitId, String exhibitName) {
        String sql = """
                update exhibit set name = ? where id = ?
                """.trim();
        jdbcTemplate.update(sql, exhibitName, exhibitId);
    }

    public List<GetExhibitWithDescriptionResponse> getAllWithDescriptions() {
        String sql = """
                select 
                e.id as exhibit_id,
                e.name as exhibit_name,
                ed.description as description,
                ed.id as description_id 
                from exhibit e
                join exhibit_description ed on e.id = ed.exhibit_id
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetExhibitWithDescriptionResponse>() {
            @Override
            public GetExhibitWithDescriptionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                GetExhibitWithDescriptionResponse response = new GetExhibitWithDescriptionResponse();
                response.setExhibitId(rs.getInt("exhibit_id"));
                response.setExhibitName(rs.getString("exhibit_name"));
                response.setDescription(rs.getString("description"));
                response.setDescriptionId(rs.getInt("description_id"));
                return response;
            }
        });
    }

    public List<Exhibit> getByShelfId(Integer id) {
        String sql = """
                select e.id as exhibit_id, e.name as exhibit_name from exhibition_exhibit ee
                join exhibit e on ee.exhibit_id = ee.id
                where ee.shelf_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), id);
    }

    public List<GetExhibitWithDescriptionResponse> getExhibitWithDescription(GenerateExhibitQrRequest dto) {
        String exhibitPlaceholders = String.join(",", Collections.nCopies(dto.getExhibitId().size(), "?"));
        // Создаем строку с ? для description_id
        String descriptionPlaceholders = String.join(",", Collections.nCopies(dto.getDescriptionId().size(), "?"));

        String sql = String.format("""
                SELECT name, description
                FROM exhibit e
                JOIN exhibit_description ed ON e.id = ed.exhibit_id
                WHERE e.id IN (%s) AND ed.id IN (%s)
                """, exhibitPlaceholders, descriptionPlaceholders);

        // Объединяем параметры
        List<Object> params = new ArrayList<>();
        params.addAll(dto.getExhibitId());
        params.addAll(dto.getDescriptionId());

        return jdbcTemplate.query(sql,  new RowMapper<GetExhibitWithDescriptionResponse>() {
            @Override
            public GetExhibitWithDescriptionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetExhibitWithDescriptionResponse.builder()
                        .exhibitName(rs.getString("name"))
                        .description(rs.getString("description"))
                        .build();
            }
        }, params.toArray());
    }
}

