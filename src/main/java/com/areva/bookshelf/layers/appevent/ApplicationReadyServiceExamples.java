package com.areva.bookshelf.layers.appevent;

import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.exceptions.ApplicationException;
import com.areva.bookshelf.layers.service.BookService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ApplicationReadyServiceExamples {
    private BookService bookService;

    public ApplicationReadyServiceExamples(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        try {
//            BookDto book = bookService.createBook(new BookDto("Lost Arkh 333", 200, Instant.now(), false, "HHHHH1123123123"));
//            System.out.println(book);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }

        try {
            bookService.deleteBook(21L);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
    }

}
