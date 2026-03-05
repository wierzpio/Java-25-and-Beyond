package ch05_api_java_18_21.ch05_04_virtual_threads;

import java.time.Duration;
import java.util.Arrays;
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
public class VirtualThreadsPooling1000Example2s {
    public static void main(final String[] args) {
        for (int factorInThousands : Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)) {
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                measureExecution(executor, "Virtual", factorInThousands);
            }

            try (var executor = Executors.newFixedThreadPool(1000)) {
                measureExecution(executor, "Pooled Plattform", factorInThousands);
            }

            try (var executor = Executors.newCachedThreadPool()) {
                measureExecution(executor, "Plattform", factorInThousands);
            }
        }
    }

    private static void measureExecution(ExecutorService executor, String info, int factor) {

        System.out.println("Start Measuring " + info);
        long start = System.nanoTime();

        try {
            for (int i = 0; i < factor; i++) {
                System.out.println("Start of " + info + " " + (i + 1) * 1000);
                submit1000Threads(executor);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }

        executor.close();
        System.out.println("End Measuring " + info);
        long end = System.nanoTime();
        System.out.println(info + " took " + (end - start) / 1_000_000 + " ms");
        System.out.println("-------------------------------");
    }

    private static void submit1000Threads(ExecutorService executor) {
        for (int i = 0; i < 1_000; i++) {
            final int pos = i;
            executor.submit(() -> {
                // Wenn man die Wartezeit hier kürzer wählt, hat man bei modernen
                // Prozessoren dann ggf. schin die ersten Threads abgearbeitet, bevor
                // die nächsten an den Executor übergeben werden
                Thread.sleep(Duration.ofSeconds(2));
                return pos;
            });
        }
    }
}
