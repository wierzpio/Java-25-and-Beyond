package ch12_all_java_22_25_ex_sol.solutions.exercise4_own_gatherer;

import java.util.ArrayList;
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
public class OwnDistinctByExampe {
    void main() {

        System.out.println("distinctBy(n -> n): " +
                Stream.of().
                        gather(distinctBy(n -> n)).toList());

        var values = Stream.of(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
        System.out.println("distinctBy(n -> n): " +
                values.gather(distinctBy(n -> n)).
                        toList());

        var names = Stream.of("Maik", "Mike", "Jim", "Tim", "Tom", "Jim",
                "Jim", "John", "Sophie", "Joelle", "Stefan",
                "Anne", "Lili", "Michael", "Andreas");
        System.out.println("distinctBy(String::length): " +
                names.gather(distinctBy(String::length)).
                        toList());

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

    static <TR, R> Gatherer<TR, ?, TR> distinctBy(Function<? super TR, ? extends R> selector) {

        Objects.requireNonNull(selector, "selector must not be null");

        // Private state to track information across elements
        class State {
            List<R> uniqueMappedValues = new ArrayList<R>();
            List<TR> uniqueValues = new ArrayList<TR>();
        }

        return Gatherer.ofSequential(
                State::new,

                Gatherer.Integrator.of((state, value, downstream) -> {

                    var mappedValue = selector.apply(value);

                    if (!state.uniqueMappedValues.contains(mappedValue)) {
                        state.uniqueMappedValues.add(mappedValue);
                        state.uniqueValues.add(value);
                    }

                    return true;
                }),

                (state, downstream) -> {
                    // Emit the distinct values
                    for (var value : state.uniqueValues) {
                        downstream.push(value);
                    }
                }
        );
    }
}
