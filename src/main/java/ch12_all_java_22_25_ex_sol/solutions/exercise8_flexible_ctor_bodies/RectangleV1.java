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
public class RectangleV1 extends ColoredShape {
    private final int width;
    private final int height;

    public RectangleV1(Color color, int x, int y, int width, int height) {
        // Trick: hier müssen wir color rein- und zurückgeben, damit die Prüfung möglich wird
        // Trick: here we have to pass color in and return it so that the checks are possible.
        super(validateParams(color, width, height), x, y);
        this.width = width;
        this.height = height;
    }

    private static Color validateParams(Color color, int width, int height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("width and height must be positive");

        return color;
    }
}