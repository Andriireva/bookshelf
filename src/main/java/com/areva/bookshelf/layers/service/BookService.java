package com.areva.bookshelf.layers.service;

import com.areva.bookshelf.layers.convert.BookConverter;
import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.exceptions.DataNotFoundException;
import com.areva.bookshelf.layers.exceptions.SemanticException;
import com.areva.bookshelf.layers.repository.BookRepository;
import org.springframework.stereotype.Service;

// This class is use for logic ...
// Example: make validation
@Service  // It is just marker. it works the same as @Component
public class BookService {

    private BookRepository bookRepository;
    private BookConverter bookConverter;

    public BookService(BookRepository bookRepository,
                       BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public BookDto createBook(BookDto bookDto) {
        // some semantic validation
        validate(bookDto);

        Book createdBook = bookRepository.createBook(bookConverter.fromDto(bookDto));
        return bookConverter.fromDomain(createdBook);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        checkExisting(id);
        validate(bookDto);

        Book createdBook = bookRepository.updateBook(id, bookConverter.fromDto(bookDto));
        return bookConverter.fromDomain(createdBook);
    }

    public void deleteBook(Long id) {
        checkExisting(id);
        bookRepository.deleteBook(id);
    }

    private void validate(BookDto bookDto) {
        if (bookDto.getPageNumbers() < 1) {
            throw new SemanticException("Page numbers must be positive");
        }
    }

    private void checkExisting(Long id) {
        Book book = bookRepository.getBook(id);
        if (book != null) {
            System.out.println("deleteBook is called with id " + id);
        } else {
            throw new DataNotFoundException("Book with id " + id + " is not found");
        }
    }
}
