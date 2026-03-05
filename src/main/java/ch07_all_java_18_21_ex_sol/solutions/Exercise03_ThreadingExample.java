package ch07_all_java_18_21_ex_sol.solutions;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Exercise03_ThreadingExample {
    public static void main(String[] args) {
        runThreads(10_000);
        System.out.println("FINISHED");
    }

    static void runThreads(int amount) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, amount).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(5));

                    boolean isVirtual = Thread.currentThread().isVirtual();
                    System.out.println("Task " + i + " finished! virtual = " + isVirtual);
                    return i;
                });
            });
        }
    }
}