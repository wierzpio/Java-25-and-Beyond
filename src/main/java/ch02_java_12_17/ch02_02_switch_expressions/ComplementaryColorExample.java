package ch02_java_12_17.ch02_02_switch_expressions;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ComplementaryColorExample {
    enum Color {RED, GREEN, BLUE, ORANGE}

    public static void main(String[] args) {
        var color = Color.RED;
        var complementaryColor = mapToComplementaryColor(color);

        System.out.println("Color: " + color.name() +
                "Complementary color " + complementaryColor.name());

        color = Color.BLUE;
        complementaryColor = mapToComplementaryColor(color);

        System.out.println("Color: " + color.name() +
                " Complementary color " + complementaryColor.name());


        color = Color.BLUE;
        complementaryColor = mapToComplementaryColorNewSyntax(color);

        System.out.println("Color: " + color.name() +
                " Complementary color " + complementaryColor.name());
    }

    private static Color mapToComplementaryColor(Color color) {
        Color complementaryColor;
        switch (color) {
            case RED:
                complementaryColor = Color.GREEN;
                break;
            case GREEN:
                complementaryColor = Color.RED;
                break;
            case BLUE:
                complementaryColor = Color.ORANGE; // $\mbox{\bfseries /* break; UPS: FALL THROUGH */}$
            case ORANGE:
                complementaryColor = Color.BLUE;
                break;
            default:
                throw new IllegalArgumentException("Unknown color: " + color);
        }
        return complementaryColor;
    }

    private static Color mapToComplementaryColorNewSyntax(Color color) {
        return switch (color) {
            case RED:
                yield Color.GREEN;
            case GREEN:
                yield Color.RED;
            case BLUE:
                yield Color.ORANGE;
            case ORANGE:
                yield Color.BLUE;
        };
    }
}
