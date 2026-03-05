package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SpecialCasesExample {

    public static void main(String[] args) {
        specialCasesAndRanges(2);
        specialCasesAndRanges(71);
        specialCasesAndRanges(-13);
    }

    static void specialCasesAndRanges(final Integer value) {
        switch (value) {
            case 0, 1, 2, 3 -> System.out.println("Special cases 0, 1, 2 or 3");
            case Integer i when i > 3 -> System.out.println("i > 3");
            case Integer i -> System.out.println("all remaining integers");
            //default -> System.out.println("unreachable");
        }
    }
}
