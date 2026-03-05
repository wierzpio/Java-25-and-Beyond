package ch02_java_12_17.ch02_02_switch_expressions;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class YieldExample {
    public static void main(final String[] args) {
        var day = SUNDAY;

        int numOfLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> {
                if (day == SUNDAY)
                    System.out.println("SUNDAY is FUN DAY");
                yield 6;
            }
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
    }
}
