package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
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
public class HomebrewDistinctBy {
    static void main() {
        Stream<String> valueStream = Stream.of("a", "bb", "ccc", "d", "e", "f");

        Set<String> resultSet = new HashSet<>();
        Set<Integer> stateSet = new HashSet<>();
        valueStream.forEach(elem -> homebrewnDistinctBy(stateSet, elem, String::length, resultSet));

        IO.println(resultSet);

        Stream<Integer> valueStream2 = Stream.of(100, 200, 1000, 2000, 3000, 7271);

        Set<Integer> resultSet2 = new HashSet<>();
        Set<String> stateSet2 = new HashSet<>();
        valueStream2.forEach(elem -> homebrewnDistinctBy(stateSet2, elem, HomebrewDistinctBy::extractFirstDigit, resultSet2));

        IO.println(resultSet2);
    }

    static <T,C> void homebrewnDistinctBy(Set<C> state, T elem, Function<T, C> func, Set<T> resultSet) {
        var converted = func.apply(elem);
        IO.println("converted: " + converted);

        if (!state.contains(converted)) {
            state.add(converted);
            resultSet.add(elem);
        }
    }

    static String extractFirstDigit(Integer value)
    {
        return "" + ("" + value).charAt(0);
    }
}
