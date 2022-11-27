package com.areva.bookshelf.layers.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorRepository {

    private JdbcTemplate jdbcTemplate; // some how it should be initiated....

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

//        debug code
//        System.out.println("AuthorRepository: Data source is + " + jdbcTemplate.getDataSource());
//        System.out.println("AuthorRepository: Data source is + " + ((HikariDataSource)jdbcTemplate.getDataSource()));
//        System.out.println("AuthorRepository: Data source DriverClassName is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getDriverClassName());
//        System.out.println("AuthorRepository: Data source url is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getJdbcUrl());
    }

    public Object getAuthor(Long id) {
        return null;
    }
}
