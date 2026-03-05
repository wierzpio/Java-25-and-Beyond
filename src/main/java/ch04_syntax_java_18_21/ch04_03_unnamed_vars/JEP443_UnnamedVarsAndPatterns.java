package ch04_syntax_java_18_21.ch04_03_unnamed_vars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class JEP443_UnnamedVarsAndPatterns {

    record Point(int x, int y) {
    }

    enum Color {RED, GREEN, BLUE}

    record ColoredPoint(Point p, Color c) {
    }

    record Order(String product) {
    }

    public static void main(String[] args) {

        motivation();

        unusedVar();

        unnamedPatternAndVars();

        specialMultiUndescoreInSwitch();

    }

    private static void motivation() {

        record Point(int x, int y) {
        }

        enum Color {RED, GREEN, BLUE}

        record ColoredPoint(Point p, Color c) {
        }

        Object obj = new Point(23, 11);

        // Pattern Matching
        if (obj instanceof Point point) {
            int x = point.x();
            int y = point.y();

            System.out.println("x: %d y: %d, sum: %d".formatted(x, y, x + y));
        }

        // Record Pattern
        if (obj instanceof Point(int x, int y)) {
            System.out.println("x: %d y: %d, sum: %d".formatted(x, y, x + y));
        }

        // "old" style
        var greenPoint = new ColoredPoint(new Point(3, 4), Color.GREEN);

        if (greenPoint instanceof ColoredPoint(Point point, Color color)) {
            System.out.println("x = " + point.x());
        }

        if (greenPoint instanceof ColoredPoint(Point(int x, int y), Color color)) {
            System.out.println("x = " + x);
        }

        interface IntTernaryOperator {
            int applyAsInt(int x, int y, int z);
        }
        IntTernaryOperator addFirstTwo = (int x, int y, int z) -> x + y; // z nicht verwendet
        BiFunction<String, String, String> doubleFirst = (String str1, String str2) -> str1.repeat(
                2); // str2 nicht verwendet

        try {
            Files.writeString(Path.of("UnnamedVars.txt"), "Underscore");
        } catch (IOException ex) {
            // just some logging
        }

        // VERY SPECIAL CASE
        IntFunction<Integer> alwaysZero = (value) -> 0;
        IntFunction<Integer> alwaysZero2 = value -> 0;
        IntFunction<Integer> alwaysZero3 = _ -> 0;
    }

    private static void unusedVar() {
        BiFunction<String, String, String> doubleFirst =
                (String str1, String _) -> str1.repeat(2);

        interface IntTriFunction {
            int apply(int x, int y, int z);
        }
        IntTriFunction addFirstTwo = (int x, int y, int _) -> x + y;

        IntTriFunction doubleSecond = (int _, int y, int _) -> y * 2;

        try {
            Files.writeString(Path.of("UnnamedVars.txt"), "Underscore");
        } catch (IOException _) {
            // java: Ab Release 21 ist nur das Unterstrichschlüsselwort "_" zulässig, um
            //  unbenannte Muster, lokale Variablen, Ausnahmeparameter oder Lambda-Parameter zu deklarieren
            //_.printStackTrace();
        }

        String userInput = "E605";
        try {
            processInput(Integer.parseInt(userInput));
        } catch (NumberFormatException _) {
            System.out.println("Expected number, but was: '" + userInput + "'");
        }

        int LIMIT = 1000;
        int total = 0;
        List<Order> orders = List.of(new Order("iPhone"), new Order("Pizza"), new Order("Water"));

        for (var _ : orders) {
            if (total < LIMIT) {
                total++;
            }
        }
        System.out.println("total" + total);
    }

    private static void processInput(int i) {
    }

    private static void unnamedPatternAndVars() {
        // unnamed pattern variable
        Point simplePoint = new Point(3, 4);
        var coloredPoint = new ColoredPoint(simplePoint, Color.GREEN);

        if (simplePoint instanceof Point(int x, int _)) {
            System.out.println("x: " + x);
        }

        // unnamed pattern variable
        if (coloredPoint instanceof ColoredPoint(Point p, Color _)) {
            System.out.println(p);
        }

        // multiple _ and mixed with and without type
        if (coloredPoint instanceof ColoredPoint(Point(int x, var _), Color _)) {
            System.out.println(x);
        }

        // unnamed pattern
        if (coloredPoint instanceof ColoredPoint(Point(int x, _), _)) {
            System.out.println("unnamed pattern, x: " + x);
        }
    }


    private static void specialMultiUndescoreInSwitch() {
        Object obj = "LDM";
        switch (obj) {
            case Byte _, Short _, Integer _, Long _ -> System.out.println("Input is a number");
            case Float _, Double _ -> System.out.println("Input is a floating-point number");
            case String _ -> System.out.println("Input is a string");
            default -> System.out.println("Object type not expected");
        }
    }
}
