package ch10_api_java_22_25.jep485_stream_gatherers;


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
public class PredefinedGathererExample {

    public static void main(String[] args) {

        windowFixed();
        windowSliding();
        foldSum();
        foldSum2();
        foldMult();
        foldRepeat();
        scanSum();
        scanCombine();
        scanRepeat();
    }


    private static void windowFixed() {
        var result = Stream.iterate(0, i -> i + 1).
                gather(Gatherers.windowFixed(4)).
                limit(3).
                toList();
        System.out.println("windowFixed(4): " + result);

        var result2 = Stream.of(0, 1, 2, 3, 4, 5, 6).
                gather(Gatherers.windowFixed(3)).
                toList();
        System.out.println("windowFixed(3): " + result2);
    }

    private static void windowSliding() {
        var result = Stream.iterate(0, i -> i + 1).
                gather(Gatherers.windowSliding(4)).
                limit(3).
                toList();
        System.out.println("windowSliding(4): " + result);

        var result2 = Stream.of(0, 1, 2, 3, 4, 5, 6).
                gather(Gatherers.windowSliding(3)).
                toList();
        System.out.println("windowSliding(3): " + result2);

        var result3 = Stream.of(0, 1, 2, 3, 4).
                gather(Gatherers.windowSliding(10)).
                toList();
        System.out.println("windowSliding(10): " + result3);
    }

    private static void foldSum() {
        var crossSum = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.fold(() -> 0L,
                        (result, number) -> result + number)).
                findFirst();
        System.out.println("sum with fold(): " + crossSum);
    }

    private static void foldSum2() {
        var crossSum = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.fold(() -> 0L,
                        (result, number) -> result + number)).
                toList();
        System.out.println("sum with fold(): " + crossSum);
    }

    private static void foldMult() {
        var crossMult = Stream.of(10, 20, 30, 40, 50).
                gather(Gatherers.fold(() -> 1L,
                        (result, number) -> result * number)).
                findFirst();
        System.out.println("mult with fold(): " + crossMult);
    }

    private static void foldRepeat() {
        var repeatedNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.fold(() -> "",
                        (result, number) ->
                                result + ("" + number).repeat(number))).
                toList();
        System.out.println("repeat with fold(): " + repeatedNumbers);
    }

    private static void scanSum() {

        var numberStrings = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.scan(() -> 0,
                        (result, number) -> result + number)).
                toList();
        System.out.println("sum with scan(): " + numberStrings);
    }

    private static void scanCombine() {

        var numberStrings = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.scan(() -> "",
                        (result, number) -> result + number)).
                toList();
        System.out.println("combine with scan(): " + numberStrings);
    }

    private static void scanRepeat() {
        var repeatedNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7).
                gather(Gatherers.scan(() -> "",
                        (result, number) ->
                                result + ("" + number).repeat(number))).
                toList();
        System.out.println("repeat with scan(): " + repeatedNumbers);
    }
}
