package ch05_api_java_18_21.ch05_04_virtual_threads;

import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class VirtualThreadsExample
{
    public static void main(String[] args)
    {
        System.out.println("Start");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor())
        //try (var executor = Executors.newCachedThreadPool())
        {
            for (int i = 0; i < 100_000; i++)
            {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(5));
                    return 42;
                });
            }
        }
        // executor.close() is called implicitly, and waits for task completion
        System.out.println("End");
    }
}
