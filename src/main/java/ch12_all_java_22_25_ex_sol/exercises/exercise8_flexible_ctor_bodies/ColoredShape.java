package ch12_all_java_22_25_ex_sol.exercises.exercise8_flexible_ctor_bodies;

import java.awt.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ColoredShape {
    private final Color color;
    private final int x;
    private final int y;

    public ColoredShape(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}

