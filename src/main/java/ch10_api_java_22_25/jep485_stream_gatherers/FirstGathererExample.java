package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class FirstGathererExample {
    public static void main(final String[] args) {

        // WISH
        /*
        var result = Stream.of("Tim", "Tom", "Jim", "Mike").
                            distinctBy(String::length).      // Hypothetical
                            toList();
        */

        var result = Stream.of("Tim", "Lea", "Jim", "Mike", "Maria", "Peter", "Sophie")
                .map(DistinctByLength::new)
                .distinct()
                .map(DistinctByLength::str)
                .toList();

        System.out.println("result: " + result);
    }

    record DistinctByLength(String str) {

        @Override
        public boolean equals(Object obj) {
            return obj instanceof DistinctByLength(String other)
                    && str.length() == other.length();
        }

        @Override
        public int hashCode() {
            return str == null ? -1 : str.length();
        }
    }
}
