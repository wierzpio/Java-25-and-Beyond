package appendix_java_8_11.a2_streams;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SortedAndDistinctExample {
    public static void main(final String[] args) {
        Stream<Integer> distinct = createIntStream().distinct();
        Stream<Integer> sorted = createIntStream().sorted();
        Stream<Integer> sortedAndDistinct = createIntStream().sorted().distinct();

        System.out.println("distinct:          " +
                           distinct.collect(Collectors.toList()));
        System.out.println("sorted:            " +
                           sorted.collect(Collectors.toList()));
        System.out.println("sortedAndDistinct: " +
                           sortedAndDistinct.collect(Collectors.toList()));
    }

    private static Stream<Integer> createIntStream() {
        return Stream.of(7, 1, 4, 3, 7, 2, 6, 5, 7, 9, 8);
    }

}