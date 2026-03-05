package ch02_java_12_17.ch02_02_switch_expressions;

import java.time.DayOfWeek;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchOldExample {
    public static void main(final String[] args) {
        DayOfWeek day = DayOfWeek.FRIDAY;

        int numOfLetters;
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                numOfLetters = 6;
                break;
            case TUESDAY:
                numOfLetters = 7;
                break;
            case THURSDAY:
            case SATURDAY:
                numOfLetters = 8;
                break;
            case WEDNESDAY:
                numOfLetters = 9;
                break;
            default:
                numOfLetters = -1;
        }
        System.out.println(numOfLetters);

        pitfall();
    }

    private static void pitfall() {
        DayOfWeek day = DayOfWeek.WEDNESDAY;

        int numOfLetters;
        switch (day) {
            case THURSDAY:
            case SATURDAY:
                numOfLetters = 8;
                break;
        }
        //System.out.println(numOfLetters);
    }

    private static void solution() {
        DayOfWeek day = DayOfWeek.WEDNESDAY;

        int numOfLetters = switch (day) {
            case THURSDAY, SATURDAY -> 8;
            default -> throw new IllegalArgumentException("Unexpected value: " + day);
        };
        System.out.println(numOfLetters);
    }
}
