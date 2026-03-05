package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Example {
    public static void main(String[] args) {
        handleTriangleAndRectangle(new Triangle(7271));
        handleTriangleAndRectangle(new Triangle(71));
        handleTriangleAndRectangle(new Rectangle(0, 0, 20, 7));
        handleTriangleAndRectangle(new Rectangle(0, 0, 200, 7));
    }

    static void handleTriangleAndRectangle(final Shape shape) {
        switch (shape) {
            case Triangle triangle when triangle.area() > 100 -> System.out.println("big triangle");
            case Triangle triangle when triangle.area() > 50 &&
                    triangle.area() <= 100 -> System.out.println("medium triangle");
            case Rectangle rect when rect.width() > 100 || rect.height() > 100 -> System.out.println("big rect");
            default -> System.out.println("Something else: " + shape);
        }
    }

}
