package com.areva.bookshelf.layers.repository;

import com.areva.bookshelf.layers.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository // Mark this Interface that it is a bean
// Spring actually creates a "dynamic" implementation of this class
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findAllByNameStartsWith(String startWith); // Dynamic method

    @Query(value = "select * from books where name = ?", nativeQuery = true) // use Native SQL Query
    List<Book> findMyBooks(String name);

    List<Book> findAllByPublishedDateBetween(Instant start, Instant end);

    Optional<Book> findByGeneralCode(String code);

}
