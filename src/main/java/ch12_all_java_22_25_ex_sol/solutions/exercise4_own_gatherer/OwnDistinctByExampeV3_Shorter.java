package ch12_all_java_22_25_ex_sol.solutions.exercise4_own_gatherer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class OwnDistinctByExampeV3_Shorter {
    void main() {

        System.out.println("distinctBy(n -> n): " +
                Stream.of().
                        parallel().
                        gather(distinctBy(n -> n)).toList());

        System.out.println("distinctBy(n -> n): " +
                Stream.of(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5).
                        parallel().
                        gather(distinctBy(n -> n)).toList());

        System.out.println("distinctBy(String::length): " +
                Stream.of("Maik", "Mike", "Jim", "Tim", "Tom", "Jim",
                                "Jim", "John", "Sophie", "Joelle", "Stefan",
                                "Anne", "Lili", "Michael", "Andreas").
                        gather(distinctBy(String::length)).toList());

        System.out.println("distinctBy(str.charAt(0)): " +
                Stream.of("Maik", "Mike", "Jim", "Tim", "Tom",
                                "Jim", "John", "Sophie", "Stefan",
                                "Anne", "Lili", "Michael", "Andreas").
                        gather(distinctBy(str -> str.charAt(0))).toList());

        System.out.println("distinctBy(str.charAt(str.length()-1))): " +
                Stream.of("Maik", "Mike", "Jim", "Tim", "Tom",
                                "Jim", "John", "Sophie", "Stefan",
                                "Anne", "Lili", "Michael", "Andreas").
                        gather(distinctBy(str -> str.charAt(str.length() - 1))).toList());
    }

    static <T, K> Gatherer<T, ?, T> distinctBy(Function<T, K> selector) {
        Objects.requireNonNull(selector, "selector must not be null");

        return Gatherer.ofSequential(HashMap::new, (state, element, downstream) -> {
            var mappedValueAsKey = selector.apply(element);
            if (!state.containsKey(mappedValueAsKey)) {
                state.put(mappedValueAsKey, element);
                downstream.push(element);
            }
            return true;
        });
    }
}
