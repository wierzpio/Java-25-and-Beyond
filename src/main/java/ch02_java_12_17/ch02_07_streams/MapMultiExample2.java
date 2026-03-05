package ch02_java_12_17.ch02_07_streams;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class MapMultiExample2
{
    public static void main(final String[] args)
    {
        // OLD
        var stream = Stream.of(List.of(1, 2, 3), "ABC", null, Set.of("X", "Y", "Z"));

        Stream<Object> expandedStream = stream.flatMap(e -> {
            if (e instanceof Iterable<?> iterable)
            {
                // Trick to convert Iterable to Stream
                return StreamSupport.stream(iterable.spliterator(), false);
            }
            return Stream.of(e);
        });

        System.out.println(expandedStream.toList());

        // NEW
        var stream2 = Stream.of(List.of(1, 2, 3), "ABC", null, Set.of("X", "Y", "Z"));
        Stream<Object> expandedStream2 = stream2.mapMulti(MapMultiExample2::expandIterable);
        System.out.println(expandedStream2.toList());
    }

    static void expandIterable(Object e, Consumer<Object> c)
    {
        if (e instanceof Iterable<?> iterable)
        {
            for (Object elem : iterable)
            {
                expandIterable(elem, c);
            }
        }
        else
        {
            c.accept(e);
        }
    }
}
