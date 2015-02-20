package com.eldest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static com.eldest.Person.Sex.FEMALE;
import static com.eldest.Person.Sex.MALE;

public class Factory {

    private static final Person[] PERSONS = {
            new Person("Anton", LocalDate.of(1985, 6, 5), MALE, "anton@mail.com"),
            new Person("Ivan", LocalDate.of(1980, 7, 22), MALE, "ivan@mail.com"),
            new Person("Maria", LocalDate.of(1986, 11, 24), FEMALE, "maria@mail.com"),
            new Person("Anna", LocalDate.of(1990, 3, 3), FEMALE, "anna@mail.com"),
            new Person("Joe", LocalDate.of(1982, 9, 15), MALE, "joe@mail.com")
    };

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transfer(
            SOURCE sourceCollection, Supplier<DEST> collectionFactory) {

        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    public static <DEST extends Collection<Person>> DEST newPersons(Supplier<DEST> collectionFactory) {
        return transfer(Arrays.asList(PERSONS), collectionFactory);
    }

    public static List<Person> newPersonsList() {
        return transfer(Arrays.asList(PERSONS), ArrayList<Person>::new);
    }
}
