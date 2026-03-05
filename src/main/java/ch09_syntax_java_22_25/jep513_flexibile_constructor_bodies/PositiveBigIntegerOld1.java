package ch09_syntax_java_22_25.jep513_flexibile_constructor_bodies;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class PositiveBigIntegerOld1 extends BaseBigInteger {
    public PositiveBigIntegerOld1(long value) {
        super(value);               // Potentially unnecessary work

        if (value <= 0)
            throw new IllegalArgumentException("non-positive value");
    }

    public static void main(String[] args) {
        new PositiveBigIntegerOld1(-4711);
    }
}