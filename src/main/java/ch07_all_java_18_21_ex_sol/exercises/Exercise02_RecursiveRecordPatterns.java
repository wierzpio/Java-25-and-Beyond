package ch07_all_java_18_21_ex_sol.exercises;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Exercise02_RecursiveRecordPatterns {

    public static void main(String[] args) {

        var p1 = new Point(1, 2);
        var p2 = new Point(3, 4);
        var p3 = new Point(5, 6);

        System.out.println(process(p1));
        System.out.println(process(new Line(p2, p3)));
        System.out.println(process(new Triangle(p1, p2, p3)));
    }

    static int process(Figure figure) {
        return switch (figure) {
            case Point(int x, int y) -> x * y;
            case Line(Point start, Point end) -> process(start) + process(end);
            // TODO
            default -> throw new IllegalStateException("Unexpected value: " + figure);
        };
    }
}
