package ch10_api_java_22_25.jep505_structured_concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class OwnJoinersExample {
    public static void main(final String[] args) throws ExecutionException, InterruptedException {
        justSuccessful();
        firstNSuccessful(4);
        meetsCondition((NetworkConnection val) -> val.type().contains("5G") || val.type().contains("4G"));
    }

    record NetworkConnection(String type) {
    }

    public static void justSuccessful() throws InterruptedException {
        JustSuccessful<NetworkConnection> joiner = new JustSuccessful<>();
        try (var scope = StructuredTaskScope.open(joiner)) {

            var result1 = scope.fork(() -> tryToGetWifi());
            var result2 = scope.fork(() -> tryToGet5g());
            var result3 = scope.fork(() -> tryToGet4g());
            var result4 = scope.fork(() -> tryToGet3g());
            var result5 = scope.fork(() -> tryToGet2gEdge());

            var result = scope.join();

            System.out.println("found connection: " + result);
        }
    }

    public static void firstNSuccessful(int maxCount) throws InterruptedException {
        FirstNSuccessful<NetworkConnection> joiner = new FirstNSuccessful<>(maxCount);
        try (var scope = StructuredTaskScope.open(joiner)) {
            var result1 = scope.fork(() -> tryToGetWifi());
            var result2 = scope.fork(() -> tryToGet5g());
            var result3 = scope.fork(() -> tryToGet4g());
            var result4 = scope.fork(() -> tryToGet3g());
            var result5 = scope.fork(() -> tryToGet2gEdge());

            var result = scope.join();

            System.out.println("found connection: " + result);
        }
    }

    public static void meetsCondition(Predicate<NetworkConnection> predicate) throws InterruptedException {
        MeetsCondition<NetworkConnection> joiner = new MeetsCondition<>(predicate);

        try (var scope = StructuredTaskScope.open(joiner)) {
            var result1 = scope.fork(() -> tryToGetWifi());
            var result2 = scope.fork(() -> tryToGet5g());
            var result3 = scope.fork(() -> tryToGet4g());
            var result4 = scope.fork(() -> tryToGet3g());
            var result5 = scope.fork(() -> tryToGet2gEdge());

            var result = scope.join();

            System.out.println("found connections: " + result);
        }
    }

    private static NetworkConnection tryToGet3g() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        return new NetworkConnection("3G");
    }

    private static void sleepRandomlyUpToOneSec() throws InterruptedException {
        Thread.sleep((long) (1000 * Math.random()));
    }

    private static NetworkConnection tryToGet4g() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
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

    private static NetworkConnection tryToGet2gEdge() throws InterruptedException {
        sleepRandomlyUpToTwoSecs();
        return new NetworkConnection("2G (Edge)");
    }

    private static void sleepRandomlyUpToTwoSecs() throws InterruptedException {
        Thread.sleep((long) (1000 + 1000 * Math.random()));
        if (Math.random() < 0.1) {
            throw new IllegalStateException("FORCED PROBLEM");
        }
    }


    static class JustSuccessful<T> implements StructuredTaskScope.Joiner<T, List<T>> {

        private final Queue<T> allSuccessfulResults = new ConcurrentLinkedQueue<>();

        @Override
        public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
            if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
                allSuccessfulResults.add(subtask.get());
            }
            return false; // nicht canceln
        }

        @Override
        public List<T> result() {
            return new ArrayList<>(allSuccessfulResults);
        }
    }

    static class FirstNSuccessful<T> implements StructuredTaskScope.Joiner<T, List<T>> {

        private final int maxResults;

        private final List<T> firstSuccessfulResults = new CopyOnWriteArrayList<>();

        public FirstNSuccessful(int maxResults) {
            this.maxResults = maxResults;
        }

        @Override
        public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
            if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
                firstSuccessfulResults.add(subtask.get());
            }

            boolean cancelProcessing = firstSuccessfulResults.size() >= maxResults;
            return cancelProcessing;
        }

        @Override
        public List<T> result() {
            // defensiv auf begrenzen (Overshoot ist möglich und ok)
            return firstSuccessfulResults.stream().limit(maxResults).toList();
        }
    }

    static class MeetsCondition<T> implements StructuredTaskScope.Joiner<T, List<T>> {

        private final Predicate<? super T> predicate;

        private final Queue<T> matchingSuccessfulResults =
                new ConcurrentLinkedQueue<>();

        public MeetsCondition(Predicate<? super T> predicate) {
            this.predicate = Objects.requireNonNull(predicate, "predicate");
        }

        @Override
        public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
            if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
                T result = subtask.get();
                if (predicate.test(result)) {
                    matchingSuccessfulResults.add(result);
                }
            }
            return false; // nie vorzeitig abbrechen
        }

        @Override
        public List<T> result() {
            return new ArrayList<>(matchingSuccessfulResults);
        }
    }


    // Macht eigentlich keinen Sinn
    static class JustFailing<T> implements StructuredTaskScope.Joiner<T, Stream<StructuredTaskScope.Subtask<? extends T>>> {

        private final List<StructuredTaskScope.Subtask<? extends T>> allFailingResults = new CopyOnWriteArrayList<>();

        public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
            if (subtask.state() != StructuredTaskScope.Subtask.State.SUCCESS) {
                allFailingResults.add(subtask);
            }
            return false;
        }

        public Stream<StructuredTaskScope.Subtask<? extends T>> result() {
            return allFailingResults.stream();
        }
    }
}