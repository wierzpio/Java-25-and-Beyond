package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class PerfectIntFloatMatches {

    record Count(int matchingCount, int nonMatchingCount) {
    }

    void main() {
        final int MIN = -10_000_000;
        final int MAX = 50_000_000;

        var result = calcMatchingStatistics(MIN, MAX);

        printStatistics(result.matchingCount,
                result.nonMatchingCount,
                MAX, MIN);
    }

    Count calcMatchingStatistics(int min, int max) {
        int matchingCount = 0;
        int nonMatchingCount = 0;

        for (int i = min; i < max; i++) {
            if (i != (int) (float) i) {
                nonMatchingCount++;
            } else {
                matchingCount++;
            }
        }

        return new Count(matchingCount, nonMatchingCount);
    }

    void printStatistics(int matchingCount,
                         int nonMatchingCount,
                         int MIN, int MAX) {
        int total = matchingCount + nonMatchingCount;

        IO.println("float/int values");
        IO.println("non matching: " + nonMatchingCount);
        IO.println("    matching: " + matchingCount);
        IO.println("       total: " + total);
        IO.println();
        IO.println("in %");
        double factor = Math.abs(100.00d / (MAX - MIN));
        IO.println("non matching %: " + (Math.round(factor * nonMatchingCount)));
        IO.println("    matching %: " + (Math.round(factor * matchingCount)));
    }
}
