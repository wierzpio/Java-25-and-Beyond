package ch04_syntax_java_18_21.ch02_01_record_patterns;

import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class JEP440_GenericRecordPatternsExample {

    interface Container<T> {
    }

    record Tuple<T>(T t1, T t2) implements Container<T> {
    }

    record Triple<T>(T t1, T t2, T t3) implements Container<T> {
    }

    public static void main(final String[] args) {

        handleContainerOld(new Tuple<>("ONE", "TWO"));
        handleContainerOld(new Triple<>("ONE", "TWO", "THREE"));

        handleContainerNew(new Tuple<>("ONE", "TWO"));
        handleContainerNew(new Triple<>("ONE", "TWO", "THREE"));

        System.out.println("nested Tuple");
        nestedOld(new Tuple<>(new Tuple<>("1-1", "1-2"), new Tuple<>("2-1", "2-2")));
        nestedOld(new Triple<>(new Tuple<>("1-1", "1-2"), new Tuple<>("2-1", "2-2"), new Tuple<>("3-1", "3-2")));
        nestedNew(new Tuple<>(new Tuple<>("1-1", "1-2"), new Tuple<>("2-1", "2-2")));
        nestedNew(new Triple<>(new Tuple<>("1-1", "1-2"), new Tuple<>("2-1", "2-2"), new Tuple<>("3-1", "3-2")));
        nestedNewV2(new Tuple<>(new Tuple<>("a", "bb"), new Tuple<>("ccc", "dddd")));
    }

    private static void handleContainerOld(final Container<String> container) {
        if (container instanceof Tuple<String>(var s1, var s2)) {
            System.out.println("Tuple: " + s1 + ", " + s2);
        } else if (container instanceof Triple<String>(var s1, var s2, var s3)) {
            System.out.println("Triple: " + s1 + ", " + s2 + ", " + s3);
        }
    }

    private static void handleContainerNew(final Container<String> container) {
        if (container instanceof Tuple(var s1, var s2)) {
            System.out.println("Tuple: " + s1 + ", " + s2);
        } else if (container instanceof Triple(var s1, var s2, var s3)) {
            System.out.println("Triple: " + s1 + ", " + s2 + ", " + s3);
        }
    }

    static void nestedOld(Container<Tuple<String>> container) {
        if (container instanceof Tuple<Tuple<String>>(
                Tuple(var s1, var s2),
                Tuple(var s3, var s4)
        )) {
            System.out.println("Tuple of strings " +
                    String.join("/", List.of(s1, s2, s3, s4)));
        } else {
            System.out.println("Container " + container);
        }
    }

    static void nestedNew(Container<Tuple<String>> container) {
        if (container instanceof Tuple(
                Tuple(String s1, String s2),
                Tuple(String s3, String s4)
        )) {
            System.out.println("Tuple of strings " + String.join("/", List.of(s1, s2, s3, s4)));
        } else {
            System.out.println("Container " + container);
        }
    }

    static void nestedNewV2(Container<Tuple<String>> container) {
        if (container instanceof Tuple(
                Tuple(var s1, var s2),
                Tuple(var s3, var s4)
        )) {
            System.out.println("Tuple of strings " +
                    String.join("/", List.of(s1.toLowerCase(),
                            s2.toUpperCase(),
                            s3.repeat(s3.length()),
                            s4)));
        } else {
            System.out.println("Container " + container);
        }
    }
}
