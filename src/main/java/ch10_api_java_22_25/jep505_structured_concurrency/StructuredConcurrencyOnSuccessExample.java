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
public class StructuredConcurrencyOnSuccessExample {
    public static void main(final String[] args) throws ExecutionException,
            InterruptedException {
        var joiner = StructuredTaskScope.Joiner.<DeliveryService>anySuccessfulResultOrThrow();
        try (var scope = StructuredTaskScope.open(joiner)) {
            var result1 = scope.fork(() -> tryToGetPostService());
            var result2 = scope.fork(() -> tryToGetFallbackService());
            var result3 = scope.fork(() -> tryToGetCheapService());
            var result4 = scope.fork(() -> tryToGetFastDeliveryService());

            DeliveryService result = scope.join();

            System.out.println("PS " + result1.state() + "/FS " + result2.state() +
                    "/CS " + result3.state() + "/FDS " + result4.state());

            System.out.println("found delivery service: " + result);
        }
    }

    private static DeliveryService tryToGetCheapService() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new DeliveryService("CheapService");
    }

    private static DeliveryService tryToGetFastDeliveryService() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new DeliveryService("FastDeliveryService");
    }

    private static DeliveryService tryToGetFallbackService() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new DeliveryService("FallbackService");
    }

    private static DeliveryService tryToGetPostService() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new DeliveryService("PostService");
    }

    private static void sleepRandomlyUpToOneSec() throws InterruptedException {
        Thread.sleep((long) (1000 + 1000 * Math.random()));
    }
}