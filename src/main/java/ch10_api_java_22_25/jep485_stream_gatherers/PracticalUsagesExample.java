package ch10_api_java_22_25.jep485_stream_gatherers;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class PracticalUsagesExample {
    static void main() {

        cummulatedExpenses();
        cummulatedExpensesByWeek();
        cummulatedExpensesPrefixSum();
    }

    private static void cummulatedExpenses() {
        var expenses = Stream.of(15, 15, 30, 25, 15, 50, 20, 10, 20, 30, 40, 50, 60, 70).
                gather(Gatherers.scan(() -> 0L,
                        (result, number) -> result + number)).
                toList();
        IO.println("cummulatedExpenses: " + expenses);
    }

private static void cummulatedExpensesByWeek() {
    var expensesByWeek = Stream.of(10, 15, 20, 30, 40, 150, 75,
                    10, 20, 30, 70, 50, 120, 100,
                    10, 50, 20, 25, 50, 150, 110
            ).
            gather(Gatherers.windowFixed(7)).
            map(window ->
                    window.stream().gather(Gatherers.scan(() -> 0,
                            (result, number) -> result + number)).toList()).
            toList();
    IO.println("cummulatedExpenses by week: " + expensesByWeek);
}

    private static void cummulatedExpensesPrefixSum() {
        var expenses = Stream.of(10, 15, 20, 30, 40, 150, 75,
                        10, 20, 30, 70, 50, 120, 100,
                        10, 50, 20, 25, 50, 150, 110
                ).
                gather(Gatherers.scan(() -> 0L,
                        (result, number) -> result + number)).
                toList();
        IO.println("cummulatedExpenses: " + expenses);

        // Abfragenn O(1), anstatt immer wieder alle Werte für jede Abfrage erneut summnieren zu müssen
        var week1 = expenses.get(6);
        var week2 = expenses.get(13) - expenses.get(6);
        var week3 = expenses.get(20) - expenses.get(13);
        IO.println("week1: " + week1);
        IO.println("week2: " + week2);
        IO.println("week3: " + week3);
    }


}
