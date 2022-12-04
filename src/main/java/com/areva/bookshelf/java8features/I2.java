package com.areva.bookshelf.java8features;

public interface I2 {
    public default String convertSome(Integer integer) {
        return "It is I2 implementation";
    }
}
