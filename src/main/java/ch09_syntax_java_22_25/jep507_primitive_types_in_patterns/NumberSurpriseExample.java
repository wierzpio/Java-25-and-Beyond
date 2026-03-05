package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NumberSurpriseExample {
    public static void main(final String[] args) {

        int byteValue = 53;
        checkType(byteValue);

        int shortValue = 32_767;
        checkType(shortValue);

        int charValue = 'M';
        checkType(charValue);

        int bigIntValue = 123_456_789;
        checkType(bigIntValue);
    }


    void checkByteAndPrintOld(int value) {
        if (value >= -128 && value <= 127) {
            byte byteValue = (byte) value;
            System.out.println("byte: " + byteValue);
        }
    }

    void checkByteAndPrint(int value) {
        if (value instanceof byte byteValue) {
            System.out.println("byte: " + byteValue);
        }
    }

    private static void checkType(long value) {
        if (value instanceof byte b) System.out.println("byte " + b);
        if (value instanceof short s) System.out.println("short " + s);
        if (value instanceof int i) System.out.println("int " + i);
        if (value instanceof long l) System.out.println("long " + l);
        if (value instanceof char c) System.out.println("char '" + c + "' (" + value + ")");
    }
}
