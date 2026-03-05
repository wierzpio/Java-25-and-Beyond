package ch10_api_java_22_25.jep505_structured_concurrency;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class DifferentJoinersExample {

    static void main() throws InterruptedException {

        // Gleichartige Ergebnisse => allSuccessfulOrThrow()
        var allSuccessful = Joiner.<String>allSuccessfulOrThrow();
        try (var scope = StructuredTaskScope.open(allSuccessful)) {
            scope.fork(() -> fetchProposal1());  // String
            scope.fork(() -> fetchProposal2());  // String

            var results = scope.join(). // Stream<Subtask<String>>
                    map(Subtask::get).
                    toList();
            IO.println("Results: " + results);
        }

        // Unterschiedliche Ergebnisse → awaitAllSuccessfulOrThrow() (default)
        // var awaitAllSuccessful = Joiner.awaitAllSuccessfulOrThrow();
        try (var scope = StructuredTaskScope.open()) { // nutzt awaitAllSuccessfulOrThrow()
            var taskProposal  = scope.fork(() -> fetchProposal1());   // String
            var taskPrices = scope.fork(() -> fetchPrices());  // List<Intgeer>
            var taskOrders = scope.fork(() -> fetchOrders());  // List<String>

            var result = scope.join();                               // null bei Erfolg, sonst FailedException
            IO.println("Results: " + result);

            String proposal = taskProposal.get();
            var prices = taskPrices.get();
            var orders = taskOrders.get();

            IO.println("reesult: " + Result.of(proposal, prices, orders));
        }

        //
        class CancelAfterTwoFailures<T> implements Predicate<Subtask<? extends T>>
        {
            private final AtomicInteger failedCount = new AtomicInteger();

            @Override
            public boolean test(Subtask<? extends T> subtask)
            {
                return subtask.state() == Subtask.State.FAILED
                        && failedCount.incrementAndGet() >= 2;
            }
        }

        var joiner = Joiner.allUntil(new CancelAfterTwoFailures<>());

        try (var scope = StructuredTaskScope.open(joiner)) { // nutzt awaitAllSuccessfulOrThrow()
            var taskProposal  = scope.fork(() -> fetchProposal1());   // String
            var taskPrices = scope.fork(() -> fetchPrices());  // List<Intgeer>
            var taskOrders = scope.fork(() -> fetchOrders());  // List<String>
            scope.join();                               // null bei Erfolg, sonst FailedException

            String proposal = taskProposal.get();
            var prices = taskPrices.get();
            var orders = taskOrders.get();

            IO.println("reesult: " + Result.of(proposal, prices, orders));
        }
    }

    record Result(String proposal, List<Integer> prices, List<String> orders) {
        public static Result of(String proposal,  List<Integer> prices, List<String> orders) {
            return new Result(proposal, prices, orders);
        }
    }

    private static List<Integer> fetchPrices() {
        return List.of(47, 11, 23);
    }

    private static List<String> fetchOrders() {
        return List.of("LDM", "EMT");
    }

    private static String fetchProposal1() {
        return "Proposal 1";
    }
    private static String fetchProposal2() {
        return "Proposal 2";
    }

}
