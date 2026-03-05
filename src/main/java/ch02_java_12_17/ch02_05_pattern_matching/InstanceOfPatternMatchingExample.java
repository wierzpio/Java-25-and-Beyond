package ch02_java_12_17.ch02_05_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class InstanceOfPatternMatchingExample {
    static String formattedOldStyle(Object obj) {
        String formatted = "unknown";
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long) {
            Long l = (Long) obj;
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            formatted = String.format("double %f", d);
        } else if (obj instanceof String) {
            String s = (String) obj;
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    static String formattedNewStyle(Object obj) {
        String formatted = "unknown";
        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }
}