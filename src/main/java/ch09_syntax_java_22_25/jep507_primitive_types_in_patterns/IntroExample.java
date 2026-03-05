package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class IntroExample {

    public static void main(final String[] args) {

        Object obj = "Sebastian";

        // Pattern Matching with instanceof
        if (obj instanceof String str && str.length() > 7) {
            System.out.println("string " + str + " is longer than 7 characters");
        } else if (obj instanceof Double d && d > 100) {
            System.out.println("double " + d + " is larger than 100");
        } else {
            System.out.println("arbitrary obj: " + obj);
        }

        // Pattern Matching with switch
        switch (obj) {
            case String str when str.length() > 7 ->
                    System.out.println("string " + str + " is longer than 7 characters");
            case Double d when d > 100 -> System.out.println("double " + d + " is larger than 100");
            default -> System.out.println("arbitrary obj: " + obj);
        }
    }
}