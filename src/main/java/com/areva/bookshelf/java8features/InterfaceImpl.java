package com.areva.bookshelf.java8features;

public interface InterfaceImpl {

    public String absMethod();

    // This is interface method with implementation
    public default String convertSome(Integer integer) {
        return String.valueOf(integer);
    }
}
