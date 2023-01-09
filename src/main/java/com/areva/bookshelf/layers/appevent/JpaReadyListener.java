package com.areva.bookshelf.layers.appevent;

import com.areva.bookshelf.layers.domain.Book;
import com.areva.bookshelf.layers.repository.BookRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaReadyListener {

    private final BookRepo bookRepo;

    public JpaReadyListener(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doJpaWork() {
        Book book = bookRepo.findById(45L).get();

        Page<Book> bookRepoAll = bookRepo.findAll(Pageable.ofSize(5)); // it will return only 5 first elementss
        Page<Book> peged25 = bookRepo.findAll(PageRequest.of(2, 5)); // get 2 page when we have 5 elements per page
        bookRepoAll.getContent(); // it returns actual data
        bookRepoAll.getTotalPages(); // it returns total pages
        System.out.println(bookRepoAll);
        List<Book> sortedByNamesDesc = bookRepo.findAll(Sort.by(Sort.Direction.DESC, "name")); // Sort by name Descend
        // bookRepo.count(Example.of()) // Example it is somethis that helps u to write "where" stements

        System.out.println(sortedByNamesDesc.get(0));
        System.out.println(sortedByNamesDesc.get(sortedByNamesDesc.size() - 1));

        // bookRepo.save() // save works as insert and update
//        bookRepo.deleteById();
        System.out.println("Book from Spring data: " + book);
    }
}
