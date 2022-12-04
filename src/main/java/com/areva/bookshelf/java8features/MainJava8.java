package com.areva.bookshelf.java8features;

import com.areva.bookshelf.layers.domain.Book;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainJava8 {
    public static void main(String[] args) {
        ClassImpl instance = new ClassImpl();
        String result = instance.convertSome(55);
        System.out.println(result);

        // Old way of doing "simple" implementation
        Calculator sum = new SumCalculate();
        Calculator multi = new MultiplyCalculator();

        // Java 8
        // it will work only wit FUnctional interface
        Calculator sumLambda = (arg1, arg2) -> arg1 + arg2;
        Calculator divide = (a, b) -> a / b;
        AnotherFuncInterface taxPriceNames = (cost, tax, prefix, someObj) -> {
            if (Optional.ofNullable(someObj).isEmpty()) {
                return prefix + (cost * tax);
            }
            return prefix + (cost * tax) + someObj.toString();
        };
        System.out.println(taxPriceNames.doSomethins(100d, 1.1d, "state tax", null));

        System.out.println("Sum " + sum.calculate(5.5, 4.5));
        System.out.println("Multi " + multi.calculate(5.5, 4.5));
        System.out.println("Sum Java8 way  " + sumLambda.calculate(5.5, 4.5));
        System.out.println("Dive Java8 way  " + divide.calculate(5.5, 4.5));

        System.out.println("===== Optional type =====");

        String s = null;
        Book book = new Book();
        book.setPageNumbers(500);  // 500 -> 250
        System.out.println(getHalfBookPageNumbers(book));

        // Stream API examples
        Book book2 = new Book("Lord of the Ring 2", 1500, Instant.now(), false, "GGGWWWW%$3333");
        Book book3 = new Book("Lord of the Ring 3", 1200, Instant.now(), false, "GG");
        Book book4 = new Book("Harry potter 1", 400, Instant.now(), true, "X");
        Book book5 = null;
        Book book6 = new Book("Gang of 4", 400, Instant.now(), false, "66aaq1123");
        Book book7 = new Book("Gang of 4", null, Instant.now(), false, "66aaq12222");
        List<Book> books = Arrays.asList(book, book2, book3, book4, book5, book5, book6, book7);

        // Get all books that are illustrated
        List<Book> iBookes = new ArrayList<>();
        for (Book b : books) {
            if (b != null && Boolean.TRUE.equals(b.getIllustrated())) {
                iBookes.add(b);
            }
        }

        // Get all books that are illustrated
        // There are 2 group of methods in stream:
        //    intermediate: all that returns Stream or Optional (example : filter, map, findFirst.... )
        //    terminal: all others are terminal (example : collect, anyMatch, forEach, reduce.... )
        List<Book> ilBooks = books.stream()
                .filter((b) -> b != null) // lambda argument will be applied to all element
                .filter((b) -> Boolean.TRUE.equals(b.getIllustrated()))

                .collect( Collectors.toList());
        System.out.println(ilBooks);

        // Get all average page numbers
        double averagePageNumbers = books.stream()
                .filter((b) -> b != null)
                .filter((b) -> b.getPageNumbers() != null)
                //.map () // It is used to convert from one type of stream to another
                .mapToInt(b -> b.getPageNumbers()) // it return IntStream
                .average()
                .orElse(0.0);

        double maxNumbers = books.stream()
                .filter((b) -> b != null)
                .filter((b) -> b.getPageNumbers() != null)
                .mapToInt(b -> b.getPageNumbers()) // it return IntStream
                .max()
                .orElse(0);

        System.out.println("averagePageNumbers = " + averagePageNumbers);
        System.out.println("maxNumbers = " + maxNumbers);

        // We want to get String books names separated via " | " sort alphabetically and unique
        String names = books.stream()
                .filter((b) -> b != null)
                .map(b -> b.getName())
                .filter(b -> b != null)
                .distinct() // get only unique values
                .sorted() // it uses element compareTo method
                .collect(Collectors.joining(" | "));
        System.out.println("Names " + names);

        // We want to get Map of general code to Book
        Map<String, Book> mapGeneralCodeBook = books.stream()
                .filter(b -> b != null)
//                .collect(Collectors.groupingBy(b -> b.getGeneralCode(), Collectors.toList()));
                .collect(Collectors.toMap(b -> b.getGeneralCode(), b -> b) );
        System.out.println(mapGeneralCodeBook);
    }

    public static Integer getHalfBookPageNumbers(Book book) {
        // traditional way
//        if (book == null) {
//            return null;
//        }
//        return book.getPageNumbers();
        return Optional.ofNullable(book)

                .map((b) -> {
                    System.out.println(" here book is not null");
                    return b.getPageNumbers();
                })
                .map((pageNumbers) -> {
                    System.out.println(" here book page numbers is not null");
                    return pageNumbers / 2;
                })
//                .orElseThrow(() -> new RuntimeException()) // it will throw exception
                .orElseGet(() -> returnValue()); // it is lazy executed
//                .orElse(returnValue()); // or else take argument that will be returns on case
                                    // ANY previous step contains value null
                //.get() // It reruns a container value or thorws exception
    }

    public static Integer returnValue() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 10;
    }
}
