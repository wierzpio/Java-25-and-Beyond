package ch02_java_12_17.ch02_03_records;

import java.util.List;
import java.util.Map;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordsAndGenericsExample {

    record Pair<T1, T2>(T1 first, T2 second) {
    }

    record MultiTypes<K, V, T>(Map<K, V> mapping, T info) {
    }

    record ListRestrictions<T>(List<T> values, int maxSize) {
        public ListRestrictions {
            if (values.size() > maxSize)
                throw new IllegalArgumentException("too many entries! got: "
                        + values.size() + ", but restricted to " + maxSize);
        }
    }

    public static void main(final String[] args) {
        // Paare mit teilweise verschiedenen Typisierungen
        pairExamples();

        // Demonstration von Generics mit verschiedenen Typen in Records
        multiTypesExamples();

        // Prüfe die Restriktion auf n Elemente (hier 5)
        listRestrictionsExamples(5);
    }

    protected static void pairExamples() {
        var pair1 = new Pair<>("INFO", 4711);
        var pair1b = new Pair<>("INFO", 4711);
        var pair2 = new Pair<>("FIRST", "SECOND");

        System.out.println(pair1);
        System.out.println(pair2);
        System.out.println(pair1.equals(pair1b) + "\n");
    }

    protected static void multiTypesExamples() {
        var mapping = Map.of("tim", 42, "tom", 77, "mike", 49);
        var multiTypes = new MultiTypes<>(mapping, "INFO");

        System.out.println(multiTypes);
        System.out.println("mapping type: " + multiTypes.mapping().getClass());
        System.out.println("info type: " + multiTypes.info().getClass() + "\n");
    }

    protected static void listRestrictionsExamples(final int limit) {
        var timAndMike = List.of("Tim", "Mike");
        var initialRestricted = new ListRestrictions<>(timAndMike, limit);
        System.out.println("values: " + initialRestricted.values());

        var names = List.of("Tim", "Tom", "Peter", "Franz", "Fritz", "Mike");
        var restrictionViolated = new ListRestrictions<>(names, limit);
    }
}
