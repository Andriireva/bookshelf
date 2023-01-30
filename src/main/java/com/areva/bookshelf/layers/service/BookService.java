package com.areva.bookshelf.layers.service;

import com.areva.bookshelf.layers.convert.BookConverter;
import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.exceptions.DataNotFoundException;
import com.areva.bookshelf.layers.exceptions.SemanticException;
import com.areva.bookshelf.layers.jms.JmsBookDeleteDto;
import com.areva.bookshelf.layers.jms.JmsBookSender;
import com.areva.bookshelf.layers.repository.BookRepo;
import com.areva.bookshelf.layers.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// This class is use for logic ...
// Example: make validation
@Service  // It is just marker. it works the same as @Component
public class BookService {

    //    private BookRepository bookRepository;
    private BookRepo bookRepo;
    private BookConverter bookConverter;
    private JmsBookSender notifier;

    public BookService(BookRepository bookRepository,
                       BookConverter bookConverter,
                       BookRepo bookRepo,
                       JmsBookSender sender) {
        this.notifier = sender;
        System.out.println("BookService constructor is called");
        this.bookRepo = bookRepo;
//        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public BookDto createBook(BookDto bookDto) {
        // some semantic validation
        validate(bookDto);

        // when Entity comes to save method with @Id is null it treated as insert SQL statement
        Book createdBook = bookRepo.save(bookConverter.fromDto(bookDto)); // <-
        return bookConverter.fromDomain(createdBook);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
//        checkExisting(id);
        validate(bookDto);

        // when Entity comes to save method with @Id filled it treated as update SQL statement
        return bookRepo.findById(id)
                .map(book -> {
                    Book foundBook = bookConverter.fromDto(bookDto);
                    foundBook.setId(book.getId());
                    return bookRepo.save(foundBook);
                })
                .map(savedBook -> bookConverter.fromDomain(savedBook))
                .orElseThrow(() -> new DataNotFoundException("Book with id " + id + " is not found"));
    }

    public void deleteBook(Long id) {
        BookDto bookToDelete = checkExisting(id);
        bookRepo.deleteById(id);
        notifier.sendDeleteMessage(new JmsBookDeleteDto(bookToDelete.getGeneralCode(),
                bookToDelete.getPublishedDate().toString()
        ));
    }

    private void validate(BookDto bookDto) {
        if (bookDto.getPageNumbers() == null || bookDto.getPageNumbers() < 1) {
            throw new SemanticException("Page numbers must be positive");
        }
    }

    private BookDto checkExisting(Long id) {
        return bookRepo
                .findById(id)
                .map(b -> {
                    System.out.println("checkExisting is called with id " + id);
                    return b;
                })
                .map(b -> bookConverter.fromDomain(b))
                .orElseThrow(() -> new DataNotFoundException("Book with id " + id + " is not found"));
    }

    public BookDto getBook(Long id) {
        return bookRepo.findById(id)
                .map(b -> bookConverter.fromDomain(b))
                .orElseThrow(() -> new DataNotFoundException("Book with id " + id + " is not found"));
    }

    public List<BookDto> getBooks() {
        return bookRepo.findAll()
                .stream()
                .map(book -> bookConverter.fromDomain(book))
                .toList();
    }

    public List<BookDto> getBooksStartWith(String nameStartWith) {
        return bookRepo.findAllByNameStartsWith(nameStartWith)
                .stream()
                .map(book -> bookConverter.fromDomain(book))
                .toList();
    }

    public List<BookDto> searchByName(String name) {
        return bookRepo.findMyBooks(name)
                .stream()
                .map(book -> bookConverter.fromDomain(book))
                .toList();
    }

    public BookDto updateBook(String generalCode, BookDto bookDto) {
        // find book be general code/
        // update it
        validate(bookDto);

        // when Entity comes to save method with @Id filled it treated as update SQL statement
        return bookRepo.findByGeneralCode(generalCode)
                .map(book -> {
                    Book foundBook = bookConverter.fromDto(bookDto);
                    foundBook.setId(book.getId());
                    return bookRepo.save(foundBook);
                })
                .map(savedBook -> bookConverter.fromDomain(savedBook))
                .orElseThrow(() -> new DataNotFoundException("Book with id " + generalCode + " is not found"));
    }
}
