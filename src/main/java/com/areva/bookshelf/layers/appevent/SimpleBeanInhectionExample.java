package com.areva.bookshelf.layers.appevent;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class SimpleBeanInhectionExample {

    private String myString;

//    public SimpleBeanInhectionExample(@Qualifier("stringbean") String myString) {
    public SimpleBeanInhectionExample(String stringTwo) {
        this.myString = stringTwo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("String :" + myString);
    }
}
