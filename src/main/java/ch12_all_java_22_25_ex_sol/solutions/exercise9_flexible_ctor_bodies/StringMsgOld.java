package ch12_all_java_22_25_ex_sol.solutions.exercise9_flexible_ctor_bodies;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class StringMsgOld extends PlainByteMsg {
    public StringMsgOld(final String payload) {
        super(convertToByteArray(payload));
    }

    // Null-Prüfung, Verarbeitung und Konvertierung gemixt
    // Mixed null-checking, processing and conversion
    private static byte[] convertToByteArray(final String payload) {
        if (payload == null)
            throw new IllegalArgumentException("payload should not be null");

        String transforemedPayload = heavyStringTransformation(payload);
        return switch (transforemedPayload) {
            case "AA" -> new byte[]{1, 2, 3, 4};
            case "BBBB" -> new byte[]{7, 2, 7, 1};
            default -> transforemedPayload.getBytes();
        };
    }

    private static String heavyStringTransformation(String input) {
        return input.repeat(2);
    }

    void main() {
        System.out.println(new StringMsgOld("A"));
        System.out.println(new StringMsgOld("BB"));
        System.out.println(new StringMsgOld("HELLO SOPHIE"));
        System.out.println(new StringMsgOld(null));
    }
}