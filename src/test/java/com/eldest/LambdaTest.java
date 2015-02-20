package com.eldest;

import org.junit.*;

import java.util.List;

import static com.eldest.Person.Sex.MALE;

public class LambdaTest {

    static List<Person> persons = Factory.newPersonsList();

    @Test
    public void testPrintPersons() throws Exception {
        Person.printPersons(
                persons,
                person -> person.getGender() == MALE
        );
    }

    @Test
    public void testProcessPersons() throws Exception {
        Person.processPersons(
                persons,
                person -> person.getGender() == MALE,
                Person::printPerson
        );
    }

    @Test
    public void testProcessPersons_2() throws Exception {
        Person.processPersons(
                persons,
                person -> person.getGender() == MALE,
                Person::getEmail,
                System.out::println
        );
    }

    @Test
    public void testGetAges() throws Exception {
        Person.processPersons(
                persons,
                person -> person.getGender() == MALE && person.getAge() > 30,
                person -> System.out.println(String.format("%s, age: %s", person, person.getAge()))
        );
    }
}
