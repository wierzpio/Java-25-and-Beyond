package ch04_syntax_java_18_21.ch02_01_record_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NestedRecordPatternsIntroExample {
    record Point(int x, int y) {
    }

    enum Color {RED, GREEN, BLUE}

    record ColoredPoint(Point point, Color color) {
    }

    record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {
    }

    record GradientLine(ColoredPoint startPoint, ColoredPoint endPoint) {
    }

    static void printColorOldStyls(Rectangle rect) {
        var upperLeft = rect.upperLeft();

        if (upperLeft != null) {
            System.out.println(upperLeft.color());
        }
    }

    public void printColorOldStyle(GradientLine line) {
        var startPoint = line.startPoint();
        if (startPoint != null) {
            System.out.println(startPoint.color());
        }
    }

    public static void printXAndColorOldStyle(Rectangle rect) {
        var upperLeft = rect.upperLeft();
        var lowerRight = rect.lowerRight();

        if (upperLeft != null && upperLeft.point() != null && lowerRight != null) {
            System.out.println("x: " + upperLeft.point().x() +
                    " / color: " + lowerRight.color());
        }
    }

    public static void printXAndColorOldStyle(GradientLine line) {
        var startPoint = line.startPoint();
        var endPoint = line.endPoint();

        if (startPoint != null && startPoint.point() != null && endPoint != null) {
            System.out.println("x: " + startPoint.point().x() +
                    " / color: " + endPoint.color());
        }
    }

    static void printXAndColor(Rectangle rect) {
        if (rect instanceof Rectangle(
                ColoredPoint(Point(int x, int y), Color color),
                ColoredPoint(Point point2, Color color2)
        )) {
            System.out.println("x: " + x + " / color: " + color2);
        }
    }

    static void printXAndColorST(Rectangle rect) {
        if (rect instanceof Rectangle(
                ColoredPoint(Point(int x, int y), Color color),
                ColoredPoint(Point point2, Color color2)
        )) {
            System.out.println("x: " + x + " / color: " + color2);
        }
    }

    public static void main(final String[] args) {
        var upperLeft = new ColoredPoint(new Point(7, 2), Color.RED);
        var lowerRight = new ColoredPoint(new Point(23, 11), Color.GREEN);

        printXAndColorOldStyle(new Rectangle(upperLeft, lowerRight));
        printXAndColor(new Rectangle(upperLeft, lowerRight));
    }
}

