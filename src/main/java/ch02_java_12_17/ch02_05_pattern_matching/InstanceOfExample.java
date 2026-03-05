package ch02_java_12_17.ch02_05_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class InstanceOfExample {
    public static void main(final String[] args) {
        Object obj2 = "Hallo Java 17";

        if (obj2 instanceof String) {
            String str2 = (String) obj2;
            if (str2.length() > 5 && str2.startsWith("Ha")) {
                System.out.println("Länge: " + str2.length());
            }
        }

        if (obj2 instanceof String str2 && str2.length() > 5 && str2.startsWith("Ha")) {
            System.out.println("Länge: " + str2.length());
        }
    }
}
