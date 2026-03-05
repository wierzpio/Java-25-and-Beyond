package ch04_syntax_java_18_21.ch04_02_pattern_matching.qualified_enums;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class QualifiedEnumExample {
    public static void main(String[] args) {
        java20Example(CompassDirection.NORTH, 50);

        java21Examaple(CompassDirection.NORTH, 50);

        java21ExamapleImproved(CompassDirection.NORTH, 50);
    }

    private static void java21ExamapleImproved(Direction dir, int speed) {
        switch (dir) {
            // Mit Bedingung
            case CompassDirection d when d == CompassDirection.NORTH && speed >= 10 -> {
                handleUp();
                handleUp();
            }
            case CompassDirection.NORTH, PlayerDirection.UP -> handleUp();
            case CompassDirection.SOUTH, PlayerDirection.DOWN -> handleDown();
            case CompassDirection.EAST, PlayerDirection.RIGHT -> handleRight();
            case CompassDirection.WEST, PlayerDirection.LEFT -> handleLeft();
        }
    }

    private static void java21Examaple(Direction dir, int speed) {
        switch (dir) {
            // Mit Bedingung
            case CompassDirection d when d == CompassDirection.NORTH && speed >= 10 -> {
                handleUp();
                handleUp();
            }
            case CompassDirection.NORTH -> handleUp();
            case CompassDirection.SOUTH -> handleDown();
            case CompassDirection.EAST -> handleRight();
            case CompassDirection.WEST -> handleLeft();
            case PlayerDirection.UP -> handleUp();
            case PlayerDirection.DOWN -> handleDown();
            case PlayerDirection.RIGHT -> handleRight();
            case PlayerDirection.LEFT -> handleLeft();
        }
    }

    private static void java20Example(Direction dir, int speed) {

        switch (dir) {
            // Mit Bedingung
            case CompassDirection d when d == CompassDirection.NORTH && speed >= 10 -> {
                handleUp();
                handleUp();
            }
            case CompassDirection d when d == CompassDirection.NORTH -> handleUp();
            case CompassDirection d when d == CompassDirection.SOUTH -> handleDown();
            case CompassDirection d when d == CompassDirection.EAST -> handleRight();
            case CompassDirection d when d == CompassDirection.WEST -> handleLeft();
            case PlayerDirection d when d == PlayerDirection.UP -> handleUp();
            case PlayerDirection d when d == PlayerDirection.DOWN -> handleDown();
            case PlayerDirection d when d == PlayerDirection.RIGHT -> handleRight();
            case PlayerDirection d when d == PlayerDirection.LEFT -> handleLeft();
            default -> throw new IllegalArgumentException("Unknown direction: " + dir);
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
