package com.eldest;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .filter(person -> person.getGender() == MALE)
                .map(Person::getEmail)
                .forEach(System.out::println);
    }

    @Test
    public void testGroupBy() throws Exception {
        Map<Person.Sex, List<Person>> mapByGender = persons
                .stream()
                .collect(
                        Collectors.groupingBy(Person::getGender));

        mapByGender.forEach((sex, persons) -> LOG.info("sex: {}, persos: {}", sex, persons));
    }

    @Test
    public void testGroupByAndMap() throws Exception {
        Map<Person.Sex, List<String>> mapByGender = persons
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.mapping(Person::getName, Collectors.toList())));

        mapByGender.forEach((sex, list) -> LOG.info("sex: {}, data: {}", sex, list));
    }

    @Test
    public void testPeek() throws Exception {
        List<String> collect = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        collect.forEach(LOG::info);
    }
}

