package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class WindowedExample {

    public static void main(String[] args) {

        // WISH
        /*
       var result = Stream.iterate(0, i -> i + 1).
                windowFixed(4).                  // Hypothetical
                limit(3).
                toList();

        // result ==> [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11]]
        */

        // WINDOWED HOMEBEWN COLLECTOR
        var resultOld
                = Stream.iterate(0, i -> i + 1)
                .limit(3 * 4)
                .collect(Collector.of(
                        () -> new ArrayList<ArrayList<Integer>>(),
                        (groups, element) -> {
                            if (groups.isEmpty() || groups.getLast().size() == 4) {
                                var current = new ArrayList<Integer>();
                                current.add(element);
                                groups.addLast(current);
                            } else {
                                groups.getLast().add(element);
                            }
                        },
                        (left, right) -> {
                            throw new UnsupportedOperationException("Cannot be parallelized");
                        }
                ));
        System.out.println("result: " + resultOld);

        var resultNew = Stream.iterate(0, i -> i + 1)
                .gather(Gatherers.windowFixed((4)))
                .limit(3)
                .toList();
        System.out.println("result: " + resultNew);


        var resultNew2 = Stream.iterate(0, i -> i + 1)
                .gather(Gatherers.windowSliding(4))
                .limit(3)
                .toList();
        System.out.println("result: " + resultNew2);
    }
}
