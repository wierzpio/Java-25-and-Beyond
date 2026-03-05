package ch02_java_12_17.ch02_07_streams;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class MapMultiExample1
{
    public static void main(String[] args)
    {
        var searchResults = List.of(Optional.of("0"), Optional.empty(), Optional.of("1"), Optional.empty(), Optional.of("2"),
                                    Optional.empty(), Optional.of("3"));

        // OLD
        searchResults.stream().
                        flatMap(Optional::stream).
                        forEach(System.out::print);

        // NEW
        searchResults.stream().
                        mapMulti(Optional::ifPresent). // !!!
                        forEach(System.out::print);

        // Mehr der Gedanke wie
        for (Optional optElem : searchResults)
        {
            optElem.ifPresent(System.out::print);
        }

        // --------------------------------------------------------------

        System.out.println();
        System.out.println("Special");
        Stream.of(Optional.of("0"), Optional.empty(), Optional.of("1"), Optional.empty(), Optional.of("2"),
                  Optional.empty(), Optional.of("3")).mapMulti(MapMultiExample1::just23).forEach(System.out::print);

        /*
        Stream.of(Optional.of("0"), Optional.empty(), Optional.of("1"), 
        		  Optional.empty(), Optional.of("2"), Optional.empty(), Optional.of("3") ).
               mapMulti(MapMultiExample1::just23_2).
        	   forEach(System.out::print);
        */

        Stream<Optional<String>> optVals = Stream.of(Optional.of("0"), Optional.empty(), Optional.of("1"),
                                                     Optional.empty(), Optional.of("2"), Optional.empty(),
                                                     Optional.of("3"));
        Stream<String> vals = optVals.mapMulti(MapMultiExample1::just23_2);

        vals.forEach(System.out::print);

        /*
        for (Optional optElem : List.of(Optional.of("0"), Optional.empty(), Optional.of("1"), 
        		  Optional.empty(), Optional.of("2"), Optional.empty(), Optional.of("3")) )
        {
        	optElem.ifPresent(System.out::print);
        }
        */
    }

    static void just23(Object obj, Consumer<Object> consumer)
    {
        if (obj instanceof Optional optElem && optElem.isPresent() && "23".contains((String) optElem.get()))
            consumer.accept(optElem.get());
    }

    // geht nicht direkt in Eclipe
    static void just23_2(Optional<String> optElem, Consumer<String> consumer)
    {
        if (optElem.isPresent() && "23".contains(optElem.get()))
            consumer.accept(optElem.get());
    }
}
