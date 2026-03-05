package ch02_java_12_17.ch02_07_streams;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
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
public class TeeingIntroTwoFilters {
    public static void main(String[] args) {
        var names = Stream.of("Michael", "Tim", "Tom", "Mike", "Bernd", "Carsten");

        final Predicate<String> startsWithMi = text -> text.startsWith("Mi");
        final Predicate<String> endsWithM = text -> text.endsWith("m");

        // $\mbox{\bfseries Wäre var hier nicht wünschenswert? => siehe Text }$
        final BiFunction<List<String>, List<String>, List<List<String>>> combineLists =
                (list1, list2) -> List.of(list1, list2);

        var result = names.collect(teeing(filtering(startsWithMi, toList()),
                filtering(endsWithM, toList()),
                combineLists));
    }

}
