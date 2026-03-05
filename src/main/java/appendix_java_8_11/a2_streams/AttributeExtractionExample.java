package appendix_java_8_11.a2_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class AttributeExtractionExample {
    public static void main(final String[] args) {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Barbara", 40, Gender.FEMALE, "Hamburg"));
        persons.add(new Person("Yannis", 5, "Hamburg"));

        // Mapping auf Name mit Lambda
        final Stream<Person> adults = persons.stream().filter(Person::isAdult);
        final Stream<String> namesStream = adults.map(person -> person.getName());

        // Mapping auf Alter mit Methodenreferenz
        final Stream<Integer> agesStream = persons.stream().map(Person::getAge).filter(age -> age >= 18);

        namesStream.forEach(System.out::println);
        agesStream.forEach(System.out::println);
    }

}