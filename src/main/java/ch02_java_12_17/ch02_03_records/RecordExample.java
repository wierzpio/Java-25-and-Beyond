package ch02_java_12_17.ch02_03_records;

import java.util.List;
import java.util.Set;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordExample {

    public static void main(String[] args) {

        MyPoint point = new MyPoint(17, 19);
        System.out.println("" + point.x() + ", " + point.y());

        var points = List.of(new MyPoint(2, 3), new MyPoint(5, 7), new MyPoint(11, 13));
        System.out.println(points);

        var pointSet = Set.of(new MyPoint(2, 3), new MyPoint(5, 7), new MyPoint(11, 13));
        System.out.println(points);

        System.out.println(points.contains(new MyPoint(5, 7)));
        System.out.println(pointSet.contains(new MyPoint(47, 11)));
    }

    private static void recordWithOwnConstructors() {
        record Point(int x, int y) {
        }
        record Dimension(int width, int height) {
        }

        record Rectangle(int x, int y, int width, int height) {
            Rectangle(Point topLeft, int width, int height) {
                this(topLeft.x, topLeft.y, width, height);
            }

            Rectangle(Point topLeft, Dimension dim) {
                this(topLeft.x, topLeft.y, dim.width, dim.height);
            }
        }
    }

    private static void recordsAsDtos() {
        record Point(int x, int y) {
        }
        record Dimension(int width, int height) {
        }
        record TopLeftWidthAndHeight(Point topLeft, int width, int heigth) {
        }
        record TopLeftAndDimension(Point topLeft, Dimension dim) {
        }

        record PlainRectangle(int x, int y, int width, int height) {
        }
        record Rectangle(Point topLeft, Dimension dim) {
        }
    }
}
