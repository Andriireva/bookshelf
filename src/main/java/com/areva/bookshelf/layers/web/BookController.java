package com.areva.bookshelf.layers.web;

import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller is a class that service as "API" that our service expose
@RestController
@RequestMapping("/books") // all mapping methods will have a "books" as a parent path
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET method to my url , it should return a book resource by ID
    // localhost - 127.0.0.1
    // port 5432 -- postgres
    // port 6777 (I dont know exacltly )  - goes to zoom messages
    // GET http://localhost:8080/books/<ID of particular book>
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    // REST best practices
    // GET http://localhost:8080/books/
    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks(@RequestParam("nameStartWith") String nameStartWith) {
        if (nameStartWith == null) {
            return bookService.getBooks();
        } else {
            return bookService.getBooksStartWith(nameStartWith);
        }
    }

    @RequestMapping(path ="/search", method = RequestMethod.GET)
    public List<BookDto> search(@RequestParam("name") String name) {
        return bookService.searchByName(name);
    }

    // POST http://localhost:8080/books/
    @RequestMapping(method = RequestMethod.POST) // POST method is ALWAYS for save new resource
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookDto bookDto) { // Spring will convert HTTP body to the instance of
                                                              // @RequestBody argument
        return bookService.createBook(bookDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }
}
