package ch05_api_java_18_21.ch05_04_virtual_threads;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class VirtualThreadsPoolingExampleV2 {
    public static void main(final String[] args) {
        var results = Map.of("Virtual", new TreeMap<Integer, Long>(),
                "Pooled Platform", new TreeMap<Integer, Long>(),
                "Platform", new TreeMap<Integer, Long>());

        for (int factorInThousands : List.of(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)) {
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                measureAndStore(executor, "Virtual", factorInThousands, results);
            }

            try (var executor = Executors.newFixedThreadPool(10000)) {
                measureAndStore(executor, "Pooled Platform", factorInThousands, results);
            }

            try (var executor = Executors.newCachedThreadPool()) {
                measureAndStore(executor, "Platform", factorInThousands, results);
            }
        }

        System.out.println(results);
    }

    private static void measureAndStore(ExecutorService executor, String type,
                                        int factorInThousands, Map<String, TreeMap<Integer, Long>> results) {
        long elapsedInMs = measureExecution(executor, type, factorInThousands);
        var parent = results.get(type);

        // Normalisierung
        parent.put(factorInThousands, elapsedInMs);
    }

    private static long measureExecution(final ExecutorService executor,
                                         final String info, final int factor) {

        System.out.println("Start Measuring " + info);
        long start = System.nanoTime();

        try {
            for (int i = 0; i < factor; i++) {
                System.out.println("Start of " + info + " " + (i + 1) * 1000);
                submit1000Threads(executor);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            // normalerweise Bad Smell, hier Indikator für Fehler
            return -1;
        }

        // Wichtig, damit man auf das Ende der Abarbeitung aller Tasks wartet
        executor.close();
        System.out.println("End Measuring " + info);
        long end = System.nanoTime();
        long elapsedInMs = (end - start) / 1_000_000;
        System.out.println(info + " took " + elapsedInMs + " ms");
        System.out.println("-------------------------------");

        return elapsedInMs;
    }

    private static void submit1000Threads(ExecutorService executor) {
        for (int i = 0; i < 1_000; i++) {
            executor.submit(() -> {
                // Wenn man die Wartezeit hier kürzer wählt, hat man bei modernen
                // Prozessoren dann ggf. schin die ersten Threads abgearbeitet, bevor
                // die nächsten an den Executor übergeben werden
                Thread.sleep(Duration.ofMillis(3000));
                return 42;
            });
        }
    }
}
