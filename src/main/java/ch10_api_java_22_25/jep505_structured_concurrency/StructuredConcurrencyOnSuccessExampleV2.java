package ch10_api_java_22_25.jep505_structured_concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class StructuredConcurrencyOnSuccessExampleV2 {
    record NetworkConnection(String type) {
    }

    public static void main(final String[] args) throws ExecutionException,
            InterruptedException {
        var joiner = StructuredTaskScope.Joiner.<NetworkConnection>anySuccessfulResultOrThrow();
        try (var scope = StructuredTaskScope.open(joiner)) {
            var result1 = scope.fork(() -> tryToGetWifi());
            var result2 = scope.fork(() -> tryToGet5g());
            var result3 = scope.fork(() -> tryToGet4g());
            var result4 = scope.fork(() -> tryToGet3g());

            NetworkConnection result = scope.join();

            System.out.println("Wifi " + result1.state() + "/5G " + result2.state() +
                    "/4G " + result3.state() + "/3G " + result4.state());

            System.out.println("found connection: " + result);
        }
    }

    private static NetworkConnection tryToGet3g() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        return new NetworkConnection("3G");
    }

    private static NetworkConnection tryToGet4g() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        if (true)
            throw new IllegalStateException("FORCED PROBLEM");

        return new NetworkConnection("4G");
    }

    private static NetworkConnection tryToGet5g() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        return new NetworkConnection("5G");
    }

    private static NetworkConnection tryToGetWifi() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        return new NetworkConnection("Wifi");
    }

    private static void sleepRandomlyUpToTwoSecs() throws InterruptedException {
        Thread.sleep((long) (1000 + 1000 * Math.random()));
    }
}