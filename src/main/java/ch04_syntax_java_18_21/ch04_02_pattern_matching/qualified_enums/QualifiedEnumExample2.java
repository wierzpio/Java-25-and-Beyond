package ch04_syntax_java_18_21.ch04_02_pattern_matching.qualified_enums;

import static ch04_syntax_java_18_21.ch04_02_pattern_matching.qualified_enums.CompassDirection.*;
import static ch04_syntax_java_18_21.ch04_02_pattern_matching.qualified_enums.PlayerDirection.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class QualifiedEnumExample2 {
    public static void main(String[] args) {
        java21Examaple(NORTH, 50);
    }

    private static void java21Examaple(Direction dir, int speed) {
        switch (dir) {
            // Mit Bedingung
            case CompassDirection d when d == NORTH && speed >= 10 -> {
                handleUp();
                handleUp();
            }
            case NORTH, UP -> handleUp();
            case SOUTH, DOWN -> handleDown();
            case EAST, RIGHT -> handleRight();
            case WEST, LEFT -> handleLeft();
        }
    }

    private static void handleLeft() {
    }

    private static void handleRight() {

    }

    private static void handleDown() {

    }

    private static void handleUp() {

    }
}
