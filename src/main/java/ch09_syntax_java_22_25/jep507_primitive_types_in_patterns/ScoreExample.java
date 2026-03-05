package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ScoreExample {
    void evaluate(final int score) {
        var result = switch (score) {
            case int value when value > 100 -> "DISQUALIFIED due to cheting";
            case int value when value >= 80 -> "excellent";
            case int value when value >= 50 -> "okaish";
            case int value when value >= 0 && value <= 50 -> "below expectations";
            default -> throw new IllegalStateException("Invalid score: " + score);
        };
        System.out.println("Your score: " + result);
    }
}
