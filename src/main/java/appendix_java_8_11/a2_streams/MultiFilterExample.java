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
public class MultiFilterExample {
    public static void main(final String[] args) {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Micha", 53, "Zürich"));
        persons.add(new Person("Micha", 41, "Aachen"));
        persons.add(new Person("Micha", 30, "Kiel"));
        persons.add(new Person("Micha", 22, "Oldenburg"));
        persons.add(new Person("Micha", 7, "Stuhr"));

        final Stream<Person> allAdultMikes = persons.stream().
                filter(Person::isAdult).
                filter(person -> person.getName().equals("Micha")).
                filter(micha -> micha.livesIn("Zürich") ||
                        micha.livesIn("Kiel"));

        allAdultMikes.forEach(System.out::println);
    }
}