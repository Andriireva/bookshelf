package com.areva.bookshelf.layers.service;

import com.areva.bookshelf.layers.convert.BookConverter;
import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.exceptions.DataNotFoundException;
import com.areva.bookshelf.layers.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// Junit is for testing
// Mockito - helps to create dependency mocks "easily"

// Unit test MUST be in-dependent from each other
class BookServiceTest {

    @InjectMocks // create new instance of BookService and try to use @Mock dependencies as arguments
    private BookService bookService; // Test object (method the we want to test

    // Dependency fields
    // @Mock will automatically provide a default return values
    @Mock // It tells Mockito to create fake instance of BookRepository class
    private BookRepository bookRepository;
    @Mock // It tells Mockito to create fake instance of BookConverter class
    private BookConverter bookConverter;

    @BeforeEach // This annotation means that this method will be run EVERY TIME before Each @Test method
    void setUp() {
        MockitoAnnotations.openMocks(this); // This code will create fake dependencies and inject it in
    }

    @Test
    void getBook() { // the most positive case, also a good style name
//    void getBookBookReturnShouldReturnBookDto() { alternative name
//    void getBook_bookExist_shouldReturnBookDto() { Andriis personal suggestion
//        BookService bookService = new BookService(
//                new BookRepositoryMock(null, null),
//                new BookConverter());

        // when is static method in org.mockito.Mockito

        // Here we tell that when bookConverter call method fromDomain with any argument value it should call a real method
        Instant publishedDate = Instant.now();
        when(bookConverter.fromDomain(any())).thenCallRealMethod();

        // Here we tell that when bookRepository call method getBook with argument 57 value it should return a value bellow
        when(bookRepository.getBook(57L)).thenReturn(Optional.of(new Book("Harry Potter", 400, publishedDate, true, null)));

        BookDto book = bookService.getBook(57L);

        assertNotNull(book);
        assertEquals("Harry Potter", book.getName());
        assertEquals(400, book.getPageNumbers());
        assertEquals(publishedDate, book.getPublishedDate());
        assertTrue(book.getIllustrated());
        assertNull(book.getGeneralCode());
    }

    @Test
    void getBookThrowException() {
//    void getBookShouldThrowDataNotFoundException() {
//    void getBook_bookNotExist_shouldThrowDataNotFoundException() {
        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> bookService.getBook(777L));

        assertEquals("Book with id 777 is not found", dataNotFoundException.getMessage());
    }

    @Test
    void updateBook() {
        Instant publishedDate = Instant.now();
        when(bookRepository.getBook(77L)).thenReturn(Optional.of(new Book("Harry Potter", 400, publishedDate, true, null)));
        when(bookConverter.fromDomain(any())).thenCallRealMethod();
        when(bookConverter.fromDto(any())).thenCallRealMethod();
        Book bookToBeUpdate = new Book("Harry Potter 2", 500, publishedDate, false, "GGGGG");
        // it is very IMPORTANT that arguments of method overrides equals and hashCode
        when(bookRepository.updateBook(77L, bookToBeUpdate)).thenReturn(Optional.of(bookToBeUpdate));

        //Bellow it is just an example of other ways to provide result of mock execution
        when(bookRepository.updateBook(99L, bookToBeUpdate)).thenThrow(new RuntimeException()); // Fake to throw a exception when update calls
        when(bookRepository.updateBook(100L, bookToBeUpdate)).thenAnswer((invocation -> invocation) ); // More flexible way of mocking result

        BookDto updatedBook = bookService.updateBook(77L, new BookDto("Harry Potter 2", 500, publishedDate, false, "GGGGG"));

        assertNotNull(updatedBook);
        assertEquals("Harry Potter 2", updatedBook.getName());
        assertEquals(500, updatedBook.getPageNumbers());
        assertEquals(publishedDate, updatedBook.getPublishedDate());
        assertFalse(updatedBook.getIllustrated());
        assertEquals("GGGGG", updatedBook.getGeneralCode());
    }

    @Test
    void updateBookAndBookDoesNotExist() {
        when(bookRepository.getBook(888L)).thenReturn(Optional.empty());

        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> bookService.updateBook(888L, new BookDto("Harry Potter 2", 500, Instant.now(), false, "GGGGG")));

        assertEquals("Book with id 888 is not found", dataNotFoundException.getMessage());
        // Verify that bookRepository update was not executed at all
        // verify is Mockito method
        verify(bookRepository, never()).updateBook(anyLong(), any()); // this line checks that bookRepository never execute with any arguments
        verify(bookRepository).getBook(888L);
        verify(bookConverter, never()).fromDomain(any());
        verify(bookConverter, never()).fromDto(any());
    }

    @Test
    void updateBookPagesAreNull() {

    }

    @Test
    void updateBookPagesAreLessThen1() {

    }

    // Old legacy-legacy mocking way
//    static class BookRepositoryMock extends BookRepository {
//
//        public BookRepositoryMock(JdbcTemplate jdbcTemplate, RowMapper<Book> bookRowMapper1) {
//            super(jdbcTemplate, bookRowMapper1);
//        }
//
//        @Override
//        public Optional<Book> getBook(Long id) {
//            if (id == 57L) {
//                return Optional.of(new Book("Harry Potter", 400, Instant.now(), true, "X"));
//            }
//            return Optional.empty();
//        }
//
//        @Override
//        public Optional<Book> updateBook(Long id, Book book) {
//            return super.updateBook(id, book);
//        }
//    }

}