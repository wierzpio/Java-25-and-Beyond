package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class TypeInferenceInSwitchRecordPatternsExample {
    record TypedContainer<T1, T2>(T1 first, T2 second) {
    }

    public static void main(String[] args) {
        recordInferenceOld(new TypedContainer<>("Michael", 52));
        recordInferenceOld(new TypedContainer<>("Sophie", 2311));

        recordInferenceJdk20(new TypedContainer<>("Michaeli", 7));
        recordInferenceJdk20(new TypedContainer<>("Michael", 52));
        recordInferenceJdk20(new TypedContainer<>("Sophie", 2311));
    }

    static void recordInferenceOld(TypedContainer<String, Integer> container) {
        switch (container) {
            case TypedContainer<String, Integer>(String text, var count) when text.contains("Michael") -> System.out.println(text + " is " + count + " years old");
            case TypedContainer<String, Integer>(var text, var count) -> System.out.println(text + count);
            default -> System.out.println("PENG");
        }
    }

    static void recordInferenceJdk20(TypedContainer<String, Integer> container) {
        switch (container) {
            case TypedContainer(var text, var count) when text.length() > 7 && count > 5 && count < 10 -> System.out.println("repeated " + text.repeat(count));
            case TypedContainer(var text, var count) when text.contains("Michael") -> System.out.println(text + " is " + count + " years old");
            case TypedContainer(var text, var count) -> System.out.println(text + count);
            default -> System.out.println("PENG");
        }
    }
}
