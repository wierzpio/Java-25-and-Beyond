package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class FloatinPointExample {
    public static void main(String[] args) {
        float val = 1.0f;
        switch (val) {
            case 1.0f -> System.out.println("1.0");
            // case 0.999999999f -> System.out.println("0.9999..");
            default -> System.out.println("something else");
        }
    }
}
