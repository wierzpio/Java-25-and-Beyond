package ch12_all_java_22_25_ex_sol.exercises.exercise6_primitive_type_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class PrimitiveTypePatterns {
    void main() {
        int value = 42;

        // necessary to work with Switch below Java 23
        Integer boxedValue = value;
        switch (boxedValue) {
            case Integer i when i > 0 && i < 10 -> log(i + " is lower than 10");
            case Integer i when i >= 10 && i < 40 -> log(i + " is lower than 40");
            case Integer i when i >= 40 && i < 70 -> log(i + " is >= 40");
            case Integer i -> log("Some unexpected value is perfect: " + i);
        }
    }

    void log(final String str) {
        System.out.println(str);
    }
}
