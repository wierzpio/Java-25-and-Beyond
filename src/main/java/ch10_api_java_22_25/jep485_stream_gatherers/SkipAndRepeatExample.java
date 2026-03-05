package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.concurrent.atomic.AtomicInteger;
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
public class SkipAndRepeatExample {

    static void main() {
        IO.println(Stream.of("This", "is", "a", "test", "A", "B", "C").
                gather(skipAndRepeat(0, 2)).
                toList());

        IO.println(Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(skipAndRepeat(4, 3)).
                toList());
    }

    public static <T> Gatherer<T, AtomicInteger, T> skipAndRepeat(int skip, int times) {
        return Gatherer.ofSequential(
                AtomicInteger::new,
                (state, element, downstream) -> {
                    int idx = state.getAndIncrement();
                    if (idx >= skip) {
                        boolean accepting = true;
                        for (int i = 0; i < times && accepting; i++) {
                            accepting = downstream.push(element);
                        }
                        return accepting; // false => sofortige Beendigung nach unten/oben
                    }
                    return true; // noch in der Skip-Phase: nichts emittiert, aber weiterlesen
                }
        );
    }
}
