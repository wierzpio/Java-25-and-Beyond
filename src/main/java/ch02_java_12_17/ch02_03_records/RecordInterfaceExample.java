package ch02_java_12_17.ch02_03_records;

import java.time.Duration;
import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordInterfaceExample {
    public static void main(final String[] args) {
        interface StockItem {
            String name();

            String description();

            double price();
        }

        record Book(String name, String description, double price, int pages) implements StockItem {
        }

        record BluRay(String name, String description, double price, Duration duration) implements StockItem {
        }

        var items = List.of(new Book("Programming Pearl", "", 27.53, 200),
                new Book("Effective Java", "", 27.53, 354),
                new BluRay("Star Wars", "Long Story", 12.34, Duration.ofMinutes(142)));

        items.forEach(item -> System.out.println(item.name()));

        double sumOfPrices = items.stream().
                map(item -> item.price()).
                mapToDouble(n -> n).
                sum();

        System.out.println("sumOfPrices: " + sumOfPrices);
    }
}
