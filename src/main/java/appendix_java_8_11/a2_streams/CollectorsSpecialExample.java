package appendix_java_8_11.a2_streams;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class CollectorsSpecialExample {
    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Stefan", "Ralph", "Andi", "Mike",
                "Florian", "Michael", "Sebastian");

        String joined = names.stream().sorted().collect(joining(", "));

        Function<String, Integer> strLength = String::length;
        Map<Integer, List<String>> grouped = names.stream().
                collect(groupingBy(strLength));
        Map<Integer, Long> counting = names.stream().
                collect(groupingBy(strLength,
                        counting()));

        System.out.println("joined: " + joined);
        System.out.println("grouped: " + grouped);
        System.out.println("counting: " + counting);
    }
}
