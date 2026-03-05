package ch02_java_12_17.ch02_07_streams;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class TeeingCollectorExample {
    public static void main(final String[] args) {
        System.out.println(calcCountAndSum(Stream.of(1, 2, 3, 4, 5, 6)));

        System.out.println(calcCountAndSum(Stream.of(2, 3, 5, 7, 11, 13, 17)));
    }

    private static LongPair calcCountAndSum(Stream<Integer> numbers) {
        return numbers.collect(teeing(counting(),
                summingLong(n -> n),
                (count, sum) -> new LongPair(count, sum)));
    }

    record LongPair(long count, long sum) {
    }
}
