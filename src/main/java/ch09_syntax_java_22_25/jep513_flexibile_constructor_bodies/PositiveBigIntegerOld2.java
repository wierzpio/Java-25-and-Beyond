package ch09_syntax_java_22_25.jep513_flexibile_constructor_bodies;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class PositiveBigIntegerOld2 extends BaseBigInteger {
    public PositiveBigIntegerOld2(final long value) {
        super(verifyPositive(value));
    }

    private static long verifyPositive(final long value) {
        if (value <= 0)
            throw new IllegalArgumentException("non-positive value");

        return value;
    }

    public static void main(String[] args) {
        new PositiveBigIntegerOld2(-4711);
    }
}