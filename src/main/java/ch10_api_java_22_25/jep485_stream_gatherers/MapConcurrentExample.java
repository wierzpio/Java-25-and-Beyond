package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class MapConcurrentExample {
    public static void main(String[] args)
    {
        IO.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).
                gather(Gatherers.mapConcurrent(5, x -> x * x)).
                toList());

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).
                parallel().
                map(x -> x * x).
                toList();


        var result3 = IntStream.rangeClosed(0, 499).
                boxed().
                parallel().
                map(LookupService::lookup).
                toList();
        IO.println(result3);
        var result4 = IntStream.rangeClosed(0, 499).
                boxed().
                gather(Gatherers.mapConcurrent(250,
                       LookupService::lookup)).
                toList();
        IO.println(result4);
        var result5 = IntStream.rangeClosed(0, 499).
                boxed().
                gather(Gatherers.mapConcurrent(500,
                        LookupService::lookup)).
                toList();
        IO.println(result5);

        // Faktor 100, aber immer noch 1 Sekunde
        var result6 = IntStream.rangeClosed(0, 49999).
                boxed().
                gather(Gatherers.mapConcurrent(50000,
                        LookupService::lookup)).
                toList();

        IO.println(result5);
    }

    static class LookupService
    {
        static int lookup(final int key)
        {
            try
            {
                Thread.sleep(1000);
                return key * key;
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
