package ch07_all_java_18_21_ex_sol.exercises;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Exercise04_SequencedCollections {
    public static void main(String[] args) {
        primeNumbers();
        createABCSet();
    }


    private static void primeNumbers() {
        List<Integer> primeNumbers = new ArrayList<>();
        primeNumbers.add(3); // [3]
        // TODO: add 2
        primeNumbers.addAll(List.of(5, 7, 11));
        // TODO: add 13

        System.out.println(primeNumbers); // [2, 3, 5, 7, 11, 13]
        // TODO print first and last element
        // TODO print reverser order

        // TODO: add 17 as last
        System.out.println(primeNumbers); // [2, 3, 5, 7, 11, 13, 17]
        // TODO print reverser order
    }

    private static void createABCSet() {
        Set<String> numbers = new LinkedHashSet<>();
        // TODO

        // TODO print first and last element
        // TODO print reverser order
    }
}