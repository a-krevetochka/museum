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
}

