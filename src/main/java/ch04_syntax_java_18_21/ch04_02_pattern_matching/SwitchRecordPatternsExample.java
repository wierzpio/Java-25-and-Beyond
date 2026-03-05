package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchRecordPatternsExample {
    public static void main(String[] args) {
        recordPatternsAndMatching(new Pos3D(7, 2, 71));
        recordPatternsAndMatching(new Pos3D(7, 2, 0));
        recordPatternsAndMatching(RgbColor.GREEN);
        recordPatternsAndMatching("MICHAEL");
    }

    record Pos3D(int x, int y, int z) {
    }

    enum RgbColor {RED, GREEN, BLUE}

    static void recordPatternsAndMatching(Object obj) {
        switch (obj) {
            case RgbColor color when color == RgbColor.RED -> System.out.println("RED WARNING");
            case RgbColor color -> System.out.println("Enum: " + color);
            case Pos3D pos when pos.z() == 0 -> System.out.println("Record: " + pos);
            case Pos3D(int x, int y, int z) when y > 0 -> System.out.println("Record decomposed: " + x + ", " +
                    y + ", " + z);
            default -> System.out.println("Something else");
        }
    }
}
