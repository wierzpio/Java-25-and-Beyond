package ch12_all_java_22_25_ex_sol.solutions.exercise8_flexible_ctor_bodies;

import java.awt.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RectangleV2 extends ColoredShape {
    private final int width;
    private final int height;

    public RectangleV2(Color color, int x, int y, int width, int height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("width and height must be positive");

        super(color, x, y);
        this.width = width;
        this.height = height;
    }
}