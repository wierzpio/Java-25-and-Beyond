package ch07_all_java_18_21_ex_sol.solutions;

import java.util.*;

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

    static void primeNumbers() {
        SequencedCollection<Integer> primeNumbers = new ArrayList<>();
        primeNumbers.add(3); // [3]
        primeNumbers.addFirst(2); // [2, 3]
        primeNumbers.addAll(List.of(5, 7, 11)); // [2, 3, 5, 7, 11]
        primeNumbers.addLast(13); // [2, 3, 5, 7, 11, 13]

        System.out.println(primeNumbers); // [2, 3, 5, 7, 11, 13]
        System.out.println(primeNumbers.getFirst()); // 2
        System.out.println(primeNumbers.getLast()); // 13
        System.out.println(primeNumbers.reversed()); // [13, 11, 7, 5, 3, 2]

        primeNumbers.addLast(17);
        System.out.println(primeNumbers); // [2, 3, 5, 7, 11, 13, 17]
        System.out.println(primeNumbers.reversed()); // [17, 13, 11, 7, 5, 3, 2]
    }

    static void createABCSet() {
        SequencedSet<String> numbers = new LinkedHashSet<>();
        numbers.addFirst("B"); // [B]
        numbers.addFirst("A"); // [A, B]
        numbers.addLast("C"); // [A, B, C]
        System.out.println(numbers);

        System.out.println(numbers.getFirst()); // A
        System.out.println(numbers.getLast()); // C
        System.out.println(numbers.reversed()); // [C, B, A]

        // TREESET IN FOLIEN ERWÄHNEN! => ADD NICHT ERLAUBT
        // getFirst()/Last() sowie removeFirst() / removeLast()
    }
}
