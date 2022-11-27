package com.areva.bookshelf.layers.repository;

import com.areva.bookshelf.layers.domain.Book;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

// IoC
// ORM - Object related mapping
// This class (layers) is responsible for communication between data storage (SQL, Files, NoSQL...)
// Spring uses java reflect package
@Repository
public class BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//
//        System.out.println("Data source is + " + jdbcTemplate.getDataSource());
//        System.out.println("Data source is + " + ((HikariDataSource)jdbcTemplate.getDataSource()));
//        System.out.println("Data source DriverClassName is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getDriverClassName());
//        System.out.println("Data source url is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getJdbcUrl());
    }

    public Book getBook(Long id) {
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class); // It is a simple implementation of row mapper
        // it should return book from DB...
        Book book = jdbcTemplate.queryForObject("select * from books where id=?", rowMapper, id);
        return book;
    }
}
