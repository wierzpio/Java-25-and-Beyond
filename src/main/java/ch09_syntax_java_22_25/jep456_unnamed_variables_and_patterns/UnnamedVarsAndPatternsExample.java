package ch09_syntax_java_22_25.jep456_unnamed_variables_and_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class UnnamedVarsAndPatternsExample {
    public static void main(final String[] args) {
        unnamedVariable("userInput: E605");

        unnamedPatternVariableAndPattern();
    }

    private static void unnamedPatternVariableAndPattern() {
        enum Color {RED, GREEN, BLUE, BLACK, WHITE}
        record Point(int x, int y) {
        }
        record ColoredPoint(Point point, Color color) {
        }

        var cp = new ColoredPoint(new Point(1234, 567), Color.BLUE);

        if (cp instanceof ColoredPoint(
                Point(
                        int x,
                        var _
                ), // $\mbox{\bfseries UNNAMED PATTERN VARIABLE}$
                _
        )) // $\mbox{\bfseries UNNAMED PATTERN}$
        {
            System.out.println("x: " + x);
        }
    }

    private static void unnamedVariable(final String userInput) {
        try {
            processInput(Integer.parseInt(userInput));
        } catch (NumberFormatException _) // $\mbox{\bfseries UNNAMED VARIABLE}$
        {
            System.out.println("Expected number, but was: '" + userInput + "'");
        }
    }

    private static void processInput(int i) {
        System.out.println("Input " + i);
    }
}
