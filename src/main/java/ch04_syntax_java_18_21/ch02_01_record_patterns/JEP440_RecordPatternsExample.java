package ch04_syntax_java_18_21.ch02_01_record_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class JEP440_RecordPatternsExample {
    record Point(int x, int y) {
    }

    static void printCoordinateInfo(Object obj) {
        if (obj instanceof Point point) {
            int x = point.x();
            int y = point.y();

            System.out.println("x: %d, y: %d, sum: %d".formatted(x, y, x + y));
        }
    }

    static void printCoordinateInfoRecordPatterns(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            System.out.println("x: %d, y: %d, sum: %d".formatted(x, y, x + y));
        }
    }

    public static void main(final String[] args) {
        printCoordinateInfo(new Point(72, 71));
        printCoordinateInfoRecordPatterns(new Point(72, 71));
    }
}