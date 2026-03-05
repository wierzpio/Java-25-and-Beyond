package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchTypeInferenceExample {

    record MyPair<T1, T2>(T1 first, T2 second) {
    }

    static void recordInferenceJdk19(MyPair<String, Integer> pair) {
        switch (pair) {
            case MyPair<String, Integer>(var text, var count)
                    when text.contains("Michael") -> System.out.println(text + " is " + count +
                    " years old");
            case MyPair<String, Integer>(var text, var count)
                    when count > 5 && count < 10 -> System.out.println("repeated " +
                    text.repeat(count));
            case MyPair<String, Integer>(var text, var count) -> System.out.println(text + count);
            default -> System.out.println("NOT HANDLED");
        }
    }

    static void recordInferenceJdk20(MyPair<String, Integer> pair) {
        switch (pair) {
            // var geht hier nicht, wenn man auf die typspezifischen
            // Methode zugreifen möchte,
            case MyPair(String text, var count)
                    when text.contains("Michael") -> System.out.println(text + " is " + count +
                    " years old");
            case MyPair(String text, Integer count)
                    when count > 5 && count < 10 -> System.out.println("repeated " +
                    text.repeat(count));
            case MyPair(var text, var count) -> System.out.println(text + count);
            default -> System.out.println("NOT HANDLED");
        }
    }

    static void recordInferenceJdk21(MyPair<String, Integer> pair) {
        switch (pair) {
            case MyPair(var text, var count) when text.contains("Michael") -> System.out.println(text + " is " + count + " years old");
            case MyPair(var text, var count) when count > 5 && count < 10 -> System.out.println("repeated " + text.repeat(count));
            case MyPair(var text, var count) -> System.out.println(text + count);
            default -> System.out.println("NOT HANDLED");
        }
    }

    public static void main(String[] args) {
        recordInferenceJdk19(new MyPair<>("Mike", 7));
        recordInferenceJdk20(new MyPair<>("Mike", 7));
        recordInferenceJdk21(new MyPair<>("Mike", 7));

        recordInferenceJdk19(new MyPair<>("Michael", 52));
        recordInferenceJdk20(new MyPair<>("Michael", 52));
        recordInferenceJdk21(new MyPair<>("Michael", 52));

        recordInferenceJdk19(new MyPair<>("Route ", 66));
        recordInferenceJdk20(new MyPair<>("Route ", 66));
        recordInferenceJdk21(new MyPair<>("Route ", 66));
    }
}
