package appendix_java_8_11.a3_misc;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class CollectionFactoryMethodsExample {
    public static void main(final String[] args) {
        // Collection Literals
        final List<String> names = List.of("MAX", "MORITZ", "MIKE");
        names.forEach(name -> System.out.println(name));

        final Set<Integer> numbers = Set.of(1, 2, 3);
        numbers.forEach(number -> System.out.println(number));

        final Map<Integer, String> mapping = Map.of(5, "five", 6, "six");
        final Map<Integer, String> mapping2 = Map.ofEntries(entry(5, "five"),
                entry(6, "six"));

        mapping.forEach((key, value) -> System.out.println(key + ":" + value));
        mapping2.forEach((key, value) -> System.out.println(key + ":" + value));
    }
}
