package com.eldest;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eldest.Person.Sex.MALE;

public class StreamTest {
    final static Logger LOG = LoggerFactory.getLogger(StreamTest.class);

    static List<Person> persons = Factory.newPersonsList();

    @Test
    public void testReduce() throws Exception {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> integerList = Arrays.asList(intArray);

        Integer sum = integerList
                .stream()
                .reduce(Integer::sum)
                .get();

        LOG.info("result {}", sum);

//        integerList.stream().reduce()


    }

    @Test
    public void testSortByAge() throws Exception {
        List<Person> result = persons
                .stream()
                .sorted(Person::compareByAge)
                .collect(Collectors.toList());

        result.stream().forEach(person -> LOG.info("{}, age: {}", person, person.getAge()));
    }

    @Test
    public void testFilter() throws Exception {
        persons
                .stream()
                .filter(person -> person.gender == MALE)
                .map(person -> person.email)
                .forEach(System.out::println);

    }
}
