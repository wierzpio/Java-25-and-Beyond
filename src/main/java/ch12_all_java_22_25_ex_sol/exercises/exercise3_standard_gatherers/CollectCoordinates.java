package ch12_all_java_22_25_ex_sol.exercises.exercise3_standard_gatherers;

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
        /* TODO BONUS */
    }

    void main() {

        var coordinates = Stream.of(0, 0, 0, 10, 20, 30, 100, 200, 300,
                        1000, 2000, 3000).
                gather(null /* TODO*/)
                /* TODO */;
        System.out.println("coordinates: " + coordinates);
    }
}
