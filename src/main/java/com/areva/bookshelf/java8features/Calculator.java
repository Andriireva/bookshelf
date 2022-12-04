package com.areva.bookshelf.java8features;


// Look on java.util.function package
// it is functional interface ( it has ONLY 1 abstract method )
@FunctionalInterface
public interface Calculator {
    public double calculate(double a, double b);

    public default double doSome(double a, double b) {
        return a - b;
    };
}
