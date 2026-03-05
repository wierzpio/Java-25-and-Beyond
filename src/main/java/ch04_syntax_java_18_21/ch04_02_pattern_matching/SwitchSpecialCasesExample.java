package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchSpecialCasesExample {
    interface Shape {
    }

    record Rectangle() implements Shape {
    }

    record Triangle() implements Shape {
        int calculateArea() {
            return 7271;
        }
    }

    static void testTriangleAndString(Object obj) {
        switch (obj) {
            case Triangle t when t.calculateArea() > 7000 -> System.out.println("Large triangle");
            case String str when str.startsWith("INFO") -> System.out.println("just an info");
            default -> System.out.println("Something else: " + obj);
        }
    }

    static void testTriangleAndString2(Object obj) {
        switch (obj) {
            case Triangle t when t.calculateArea() > 7000 -> System.out.println("Large triangle");
            case String str when str.startsWith("INFO") && str.contains("SPECIAL") -> System.out.println("a very special info");
            case String str when str.startsWith("INFO") -> System.out.println("just an info");
            // not detected ...
            case String str when str.startsWith("INFO") && str.contains("SPECIAL") -> System.out.println("a very special info");
            default -> System.out.println("Something else: " + obj);
        }
    }

    public static void main(String[] args) {
        testTriangleAndString(new Triangle());
        testTriangleAndString("INFO: switch and when");
        testTriangleAndString("INFO: switch and SPECIAL");
        testTriangleAndString2("INFO: switch and SPECIAL");
        testTriangleAndString("Michael");
    }
}
