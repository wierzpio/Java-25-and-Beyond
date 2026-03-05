package ch12_all_java_22_25_ex_sol.solutions.exercise3_standard_gatherers;

import java.util.List;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class CollectCoordinates {
	record Point3D(int x, int y, int z) {
        // BONUS:
        Point3D(List<Integer> values)
        {
            if (values.size() != 3) {
                throw new IllegalArgumentException("Wrong number of values, " +
                                                   "expected 3 but got " + values.size());
            }

            this(values.get(0), values.get(1), values.get(2));
        }
    }

    void main() {

        var coordinates = Stream.of(0, 0, 0, 10, 20, 30, 100, 200, 300, 1000, 2000, 3000).
                gather(java.util.stream.Gatherers.windowFixed(3)).
                map(window -> new Point3D(window.get(0), window.get(1), window.get(2))).
                toList();
        System.out.println("coordinates: " + coordinates);

        var coordinates2 = Stream.of(0, 0, 0, 10, 20, 30, 100, 200, 300, 1000, 2000, 3000).
                gather(java.util.stream.Gatherers.windowFixed(3)).
                map(window -> new Point3D(window)).
                toList();
        System.out.println("coordinates2: " + coordinates2);
    }
}
