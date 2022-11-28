package com.areva.bookshelf.layers.convert;

import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    public BookDto fromDomain(Book book) {
        return new BookDto(book.getName(), book.getPageNumbers(), book.getPublishedDate(), book.getIllustrated(), book.getGeneralCode());

    }

    public Book fromDto(BookDto bookDto) {
        return new Book(bookDto.getName(),
                bookDto.getPageNumbers(), bookDto.getPublishedDate(), bookDto.getIllustrated(), bookDto.getGeneralCode());
    }
}
