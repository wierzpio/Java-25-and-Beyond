package ch10_api_java_22_25.jep502_stable_values;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class InitialStableValueExample {
    static final StableValue<String> SHARED_INFO = StableValue.<String>of();
    static final StableValue<Integer> CHECKSUM = StableValue.of(42);

    public static void main(String[] args) {
        // Zugriff auf einmalig initialisierten Wert
        System.out.println(CHECKSUM);

        // Initialize once
        String message = SHARED_INFO.orElseSet(() -> "FIRST CONTENT");
        System.out.println(message);

        // Second try results in Exception
        SHARED_INFO.setOrThrow("CHANGED CONTENT");
        // Exception in thread "main" java.lang.IllegalStateException: The contents is already set
        //	at java.base/jdk.internal.lang.stable.StableValueImpl.setOrThrow(StableValueImpl.java:98)
        //	at jep502_Stable_Values_TODO.InitialStableValueExample.main(InitialStableValueExample.java:11)
    }
}
