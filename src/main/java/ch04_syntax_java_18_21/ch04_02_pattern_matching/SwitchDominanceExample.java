package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchDominanceExample {

    public static void main(String[] args) {
        Integer value = calcValue();
        switch (value) {
            case -1, 0, 1 -> System.out.println("Handle special cases -1, 0 or 1");
            case Integer i when i >= 1 -> System.out.println("Handle positive integer cases i > 1");
            case Integer i -> System.out.println("Handle all the remaining integers");
        }

        dominanceExampleWithConstant("Michael");
        dominanceExampleWithConstant("Sophie");
    }

    static int calcValue() {
        return 4711;
    }

    static void dominanceExample(Object obj) {
        switch (obj) {
            case null -> System.out.println("null");
            case String str -> System.out.println(str.toLowerCase());
            case Integer i -> System.out.println(i * i);
            default -> {
            }
        }
    }

    // Fehler im Eclipse-Compiler bei Dominance-Check, unreachable wird nicht erkannt
    static void dominanceExampleWithConstant(Object obj) {
        switch (obj.toString()) {
            case "Sophie" -> System.out.println("My lovely daughter");
            case String str when str.length() > 5 -> System.out.println(str.strip());
            default -> System.out.println("FALLBACK");
        }
    }
}