package com.areva.bookshelf.layers.appevent;

import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

// it will be scanned by spring and it will be controlled by Spring
//@Component
public class ApplicationReadyRepositoryExamples {

    private BookRepository bookRepository;

    public ApplicationReadyRepositoryExamples(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method that are marked as EventListener(ApplicationReadyEvent.class) will
    // be run ONCE when project is ready
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        Book book = bookRepository.getBook(11L);
        System.out.println(book.toString());

        Book bookTobeCreated = new Book("Lord of the Ring 2", 1500, Instant.now(), false, "GGGWWWW%$3333");
        Book createdBook = bookRepository.createBook(bookTobeCreated);
        System.out.println(createdBook);

        Book bookToBeUpdated = new Book("Lord of the Ring 2 UPDATED", 3000, Instant.now(), true, "UPDATED _GGGWWWW%$3333");
        Book updatedBook = bookRepository.updateBook(11L, bookToBeUpdated);
        System.out.println(updatedBook);

        bookRepository.deleteBook(2L);
    }
}
