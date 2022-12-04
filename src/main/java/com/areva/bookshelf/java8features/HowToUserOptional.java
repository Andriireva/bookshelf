package com.areva.bookshelf.java8features;

import java.util.Collection;
import java.util.Optional;

public class HowToUserOptional {

    // Optional can be used in return but MUST not used as method arguments
    public Optional<String> doSome(Optional<Integer> o) {
//        o.filter()
        return null;
    }

    // it is bad practice
    public Optional<Collection> getSome() {
        return null;
    }
}
