package com.areva.bookshelf.layers.appevent;

import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// it will be scanned by spring and it will be controlled by Spring
@Component
public class ApplicationReady {

    private BookRepository bookRepository;

    public ApplicationReady(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method that are marked as EventListener(ApplicationReadyEvent.class) will
    // be run ONCE when project is ready
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        Book book = bookRepository.getBook(2L);
        System.out.println(book.toString());
    }
}
