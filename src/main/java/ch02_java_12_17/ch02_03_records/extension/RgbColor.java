package ch02_java_12_17.ch02_03_records.extension;

import java.util.Objects;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
record RgbColor(int red, int green, int blue) {
    public RgbColor {
        Objects.checkIndex(red, 256);
        Objects.checkIndex(green, 256);
        Objects.checkIndex(blue, 256);
    }

    public String asHex() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}