package ch10_api_java_22_25.jep484_class_file_api.bonus;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ExampleDebug {

    void main() {
        debugOutput1();
        debugOutput2();
        System.out.println("Normal Statement");
    }

    public static void debugOutput1() {
        System.out.println("Some JEPs");
    }

    public static void debugOutput2() {
        System.out.println("Noch mehr JEPs");
    }

}
