package ch12_all_java_22_25_ex_sol.exercises.exercise11_structured_concurrency;

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
    record NetworkConnection(String type) {
    }

    void main() {
        var joiner = StructuredTaskScope.Joiner.<NetworkConnection>anySuccessfulResultOrThrow();
        try (var scope = StructuredTaskScope.open(joiner)) {
            // TODO
            StructuredTaskScope.Subtask<NetworkConnection> result1 = null;
            StructuredTaskScope.Subtask<NetworkConnection> result2 = null;
            StructuredTaskScope.Subtask<NetworkConnection> result3 = null;
            StructuredTaskScope.Subtask<NetworkConnection> result4 = null;

            NetworkConnection result = null;             // TODO

            System.out.println("Wifi " + result1.state() +
                    "/5G " + result2.state() +
                    "/4G " + result3.state() +
                    "/3G " + result4.state());
            System.out.println("found connection: " + result);
        }
    }

    private static NetworkConnection tryToGet3g() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new NetworkConnection("3G");
    }

    private static NetworkConnection tryToGet4g() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new NetworkConnection("4G");
    }

    private static NetworkConnection tryToGet5g() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new NetworkConnection("5G");
    }

    private static NetworkConnection tryToGetWifi() throws InterruptedException {
        sleepRandomlyUpToOneSec();
        return new NetworkConnection("Wifi");
    }

    private static void sleepRandomlyUpToOneSec() throws InterruptedException {
        Thread.sleep((long) (1000 * Math.random()));
    }
}