package com.eldest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Person {

    enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String email;

    public Person(String name, LocalDate birthday, Sex gender, String email) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    public void printPerson() {
        System.out.println(toString());
    }

    public static void printPersons(List<Person> roster, Predicate<Person> tester) {
        for (Person person : roster) {
            if (tester.test(person)) {
                person.printPerson();
            }
        }
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> consumer) {
        for (Person person : roster) {
            if (tester.test(person)) {
                consumer.accept(person);
            }
        }
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester,
                                      Function<Person, String> mapper, Consumer<String> consumer) {

        for (Person person : roster) {
            if (tester.test(person)) {
                String data = mapper.apply(person);
                consumer.accept(data);
            }
        }
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    @Override
    public String toString() {
        return String.format("name: %s, gender: %s, birthday: %s, email: %s", name, gender, birthday, email);
    }
}
