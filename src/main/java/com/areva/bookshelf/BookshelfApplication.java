package com.areva.bookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshelfApplication {

	public static void main(String[] args) {
//		DataSource source = new SimpleDriverDataSource();
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
//		BookRepository bookRepository = new BookRepository(jdbcTemplate);
//		Book book = bookRepository.getBook(4L);

		SpringApplication.run(BookshelfApplication.class, args);
	}

}
