package ch12_all_java_22_25_ex_sol.solutions.exercise3_standard_gatherers;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 /**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class FindTemperatureJumps {
    void main() {

        // Find temperature jumps of 20 or more degrees
        var temps = Stream.of(10, -7, 20, 0, 12, 17, 20, 40, 10, 20, -2).
                gather(Gatherers.windowSliding(2)).
                filter(window -> isRelevantTempDiff(window, 20)).
                toList();

        System.out.println("temp jumps: " + temps);
    }

    private static boolean isRelevantTempDiff(List<Integer> window, int threshold) {
        return Math.abs(window.get(0) - window.get(1)) >= threshold;
    }
}
