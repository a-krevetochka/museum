package com.mgtu.museum.repository;

import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.entity.Exhibit;
import com.mgtu.museum.mapper.ExhibitMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
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
                id as exhibit_id,\s
                name as exhibit_name
                from exhibit where name like "%?%"
                """.trim();
        return jdbcTemplate.query(sql, new ExhibitMapper(), name);
    }

    public Exhibit findByReceiptNumber(String receiptNumber) throws SQLException {
        String sql = """
                 select\s
                 id as exhibit_id,\s
                 name as exhibit_name
                 from exhibit where receipt_number = ?
                \s""".trim();
        Optional<Exhibit> exhibit = jdbcTemplate.query(sql, new ExhibitMapper(), receiptNumber).stream().findFirst();
        return exhibit.orElseGet(() -> getFrom2DB(receiptNumber));
    }

    private Exhibit getFrom2DB(String receiptNumber) {
        throw new EntityNotFoundException("Экспоната не существует");
//        String url = "jdbc:postgresql://localhost:5432/your_secondary_database_name"; // Replace with your database URL
//        String user = "your_username"; // Replace with your username
//        String password = "your_password"; // Replace with your password
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//            try (Statement statement = connection.createStatement()) {
//                try (Connection dbConnection = DriverManager.getConnection(url, user, password)) {
//                    try (Statement dbStatement = dbConnection.createStatement()) {
//                        ResultSet set = dbStatement.executeQuery(String.format("select" +
//                                "id as exhibit_id" +
//                                "name as exhibit_name" +
//                                "from exhibit where receipt_number = %s", receiptNumber).trim());
//                        if (set.next()) { // Check if a row was returned
//                            Exhibit exhibit = new Exhibit();
//                            exhibit.setId(set.getInt("id"));
//                            exhibit.setName(set.getString("name"));
//                            return exhibit;
//                        } else {
//                            throw new EntityNotFoundException("Экспоната нет");
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
