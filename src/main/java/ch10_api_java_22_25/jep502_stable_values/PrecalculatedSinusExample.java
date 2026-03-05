package ch10_api_java_22_25.jep502_stable_values;

import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public final class PrecalculatedSinusExample {
    // 0..359 Grad → sin(Grad) (lazy pro Index, danach stabil)
    public static final List<Double> PRECALCULATED_SIN =
            StableValue.list(360, i -> Math.sin(Math.toRadians(i)));

    // Hilfszugriff mit Normalisierung in [0,360)
    public static double sin(int degree) {
        return PRECALCULATED_SIN.get(degree % 360);
    }

    void main() {
        List.of(0, 45, 90, 180, 270, 360, 450).forEach( degree ->
            System.out.println(sin(degree)));
    }
}
