package com.areva.bookshelf.layers.jms;

import com.areva.bookshelf.layers.dto.BookDto;
import com.areva.bookshelf.layers.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class BookJmsListener {

    private ObjectMapper objectMapper;
    private BookService bookService;

    public BookJmsListener(ObjectMapper objectMapper, // Spring will use prepared bean
                           BookService bookService) {
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    // we want to have entrance to read raw data from particular queue, we want to save
    @JmsListener(
            destination = "singlebook" // Here we put a JMS queue name
    )
    public void readData(Message<String> message) { // it is raw
        // System.out.println(message.getPayload());
        JmsBookDto jmsBookDto = null; // This statement convert from String to the class
        try {
            jmsBookDto = objectMapper.readValue(message.getPayload(), JmsBookDto.class);
            bookService.createBook(new BookDto(
                    jmsBookDto.getBookName(),
                    jmsBookDto.getpNumbers(),
                    jmsBookDto.getpDate(),
                    null, null));
        } catch (JsonProcessingException e) {
            System.out.println("message data is not JSON. Raw data: " + message.getPayload());
        }
    }

    @JmsListener(
            destination = "updatesbook" // Here we put a JMS queue name
    )
    public void updateData(Message<String> message) { // it is raw
        // System.out.println(message.getPayload());
        JmsBookDto jmsBookDto = null; // This statement convert from String to the class
        try {
            jmsBookDto = objectMapper.readValue(message.getPayload(), JmsBookDto.class);

            //
            bookService.updateBook(jmsBookDto.getGeneralCode(), new BookDto(
                    jmsBookDto.getBookName(),
                    jmsBookDto.getpNumbers(),
                    jmsBookDto.getpDate(),
                    null, jmsBookDto.getGeneralCode()));
        } catch (JsonProcessingException e) {
            System.out.println("message data is not JSON. Raw data: " + message.getPayload());
        }
    }


}
