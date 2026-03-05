package ch10_api_java_22_25.jep505_structured_concurrency;

import java.time.Duration;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class StructuredConcurrencyExample {
    void main() throws Exception {
/*        IO.println(measureExecution(
                () -> "sync: " + handleSynchronously(4711L)) + " ms");
        IO.println(measureExecution(
                () -> "old style: " + handleExecutorService(4711L)) + " ms");
        IO.println(measureExecution(
                () -> "new style: " + handleStructuredTaskScope(4711L)) + " ms");*/
        IO.println(measureExecution(
                () -> "new style: " + handleWithTimeout(4711L, Duration.ofMillis(500))) + " ms");
    }

    static long measureExecution(Callable<String> action) throws Exception {
        final long startTime = System.nanoTime();
        String result = action.call();
        final long endTime = System.nanoTime();
        System.out.println(result);

        return TimeUnit.MILLISECONDS.convert(endTime - startTime, NANOSECONDS);
    }

    static Response handleSynchronously(final Long userId) throws InterruptedException {
        var user = findUser(userId);
        var order = fetchOrder(userId);

        return new Response(user, order);
    }

    static Response handleExecutorService(Long userId) throws ExecutionException,
            InterruptedException {
        try (var exectuorService = Executors.newCachedThreadPool()) {
            var userFuture = exectuorService.submit(() -> findUser(userId));
            var orderFuture = exectuorService.submit(() -> fetchOrder(userId));

            var user = userFuture.get();   // Join findUser
            var order = orderFuture.get();  // Join fetchOrder

            return new Response(user, order);
        }
    }

//    static Response handleJava24(Long userId) throws ExecutionException, InterruptedException
//    {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            StructuredTaskScope.Subtask<String> userSubtask  = scope.fork(() -> findUser(userId));
//            StructuredTaskScope.Subtask<Integer> orderSubtask = scope.fork(() -> fetchOrder(userId));
//
//            scope.join();          // Join both forks
//            scope.throwIfFailed(); // ... and propagate errors
//
//            // Here, both forks have succeeded, so compose their results
//            return new Response(userSubtask.get(), orderSubtask.get());
//        }
//    }

    static Response handleStructuredTaskScope(Long userId) throws InterruptedException {
        //var joiner = StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow();
        try (var scope = StructuredTaskScope.open()) {
            StructuredTaskScope.Subtask<String> userSubtask = scope.fork(() -> findUser(userId));
            StructuredTaskScope.Subtask<Integer> orderSubtask = scope.fork(() -> fetchOrder(userId));

            // join() liefert null im Erfolgsfall!!!
            scope.join();          // Join both forks

            // Here, both forks have succeeded, so compose their results
            return new Response(userSubtask.get(), orderSubtask.get());
        }
    }

//    static Response handleWithTimeout(Long userId, Duration timeOut) throws ExecutionException, InterruptedException, TimeoutException {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            var userSubtask  = scope.fork(() -> findUser(userId));
//            var orderSubtask  = scope.fork(() -> fetchOrder(userId));
//
//            scope.joinUntil(Instant.now().plus(timeOut));
//            scope.throwIfFailed(); // ... and propagate errors
//
//            // Here, both forks have succeeded, so compose their results
//            return new Response(userSubtask .get(), orderSubtask.get());
//        }
//    }

    static Response handleWithTimeout(Long userId, Duration timeout) throws InterruptedException {
        var joiner = StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow();
        try (var scope = StructuredTaskScope.open(joiner,
                config -> config.withTimeout(timeout))) {
            var userSubtask = scope.fork(() -> findUser(userId));
            var orderSubtask = scope.fork(() -> fetchOrder(userId));

            scope.join();

            return new Response(userSubtask.get(), orderSubtask.get());
        }
    }

    static String findUser(Long userId) throws InterruptedException {
        Thread.sleep(2_000);
        return "Michael";
    }


    static Integer fetchOrderOrig(Long userId) throws InterruptedException {
        Thread.sleep(3_000);
        return 42;
    }

    // static Integer endlssfetchOrder(Long userId) throws InterruptedException {
    static Integer fetchOrder(Long userId) throws InterruptedException {
        System.out.println("Start");
        boolean stopped = false;
        while (!stopped) {

            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                //stopped = true;
                System.out.println("Interrupted");
            }

            System.out.println("Lets go");
        }
        return 42;
    }

    record Response(String userName, Integer orderNo) {
    }
}