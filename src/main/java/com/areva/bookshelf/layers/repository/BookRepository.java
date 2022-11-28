package com.areva.bookshelf.layers.repository;

import com.areva.bookshelf.layers.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

// IoC
// ORM - Object related mapping
// This class (layers) is responsible for communication between data storage (SQL, Files, NoSQL...)
// Spring uses java reflect package
// CRUD operations ( create, retrieve, update and delete )
@Repository
public class BookRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Book> rowMapper;

    public BookRepository(JdbcTemplate jdbcTemplate,
                          RowMapper<Book> bookRowMapper1) { // it will inject (use) bean with name "bookRowMapper1", see ApplicationConfig class
        this.jdbcTemplate = jdbcTemplate;
//
//        System.out.println("Data source is + " + jdbcTemplate.getDataSource());
//        System.out.println("Data source is + " + ((HikariDataSource)jdbcTemplate.getDataSource()));
//        System.out.println("Data source DriverClassName is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getDriverClassName());
//        System.out.println("Data source url is + " + ((HikariDataSource)jdbcTemplate.getDataSource()).getJdbcUrl());
        this.rowMapper = bookRowMapper1;
    }

    // If data is not found in repository we return null or return Optinal<> type ( java 8 feature)
    public Book getBook(Long id) {


        // it should return book from DB...
        try {
            Book book = jdbcTemplate.queryForObject("select * from books where id=?", rowMapper, id);
            return book;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Book createBook(Book book) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO public.books(\n" +
                                "\tname, page_numbers, published_date, illustrated, general_code)\n" +
                                "\tVALUES (?, ?, ?, ?, ?)",
                        new String[]{"id"});
                preparedStatement.setString(1, book.getName());
                preparedStatement.setInt(2, book.getPageNumbers());
                preparedStatement.setTimestamp(3, Timestamp.from(book.getPublishedDate())); // static method from
                preparedStatement.setBoolean(4, book.getIllustrated());
                preparedStatement.setString(5, book.getGeneralCode());

                return preparedStatement;
            }
        },
                keyHolder);

        long generatedKey = keyHolder.getKey().longValue();

        return getBook(generatedKey);
    }

    public Book updateBook(Long id, Book book) {
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                                    PreparedStatement preparedStatement = con.prepareStatement("UPDATE public.books\n" +
                                            "\tSET name=?, page_numbers=?, published_date=?, illustrated=?, general_code=?\n" +
                                            "\tWHERE id = ?");
                                    preparedStatement.setString(1, book.getName());
                                    preparedStatement.setInt(2, book.getPageNumbers());
                                    preparedStatement.setTimestamp(3, Timestamp.from(book.getPublishedDate())); // static method from
                                    preparedStatement.setBoolean(4, book.getIllustrated());
                                    preparedStatement.setString(5, book.getGeneralCode());
                                    preparedStatement.setLong(6, id);

                                    return preparedStatement;
                                }
                            });

        return getBook(id);
    }

    public void deleteBook(Long id) {
        jdbcTemplate.update("delete from books where id = ?", id);
    }
}
