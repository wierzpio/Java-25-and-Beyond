package ch05_api_java_18_21.ch05_03_sequenced_collection;

import java.util.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SequencedCollectionExample {
    public static void main(String[] args) {
        sequenceCollectionExample();

        sequenceSetExample();

        modifiyOriginalAndView();
    }

    public static void sequenceCollectionExample() {
        System.out.println("Processing letterSequence with list");
        SequencedCollection<String> letterSequence = List.of("A", "B", "C", "D", "E");
        System.out.println(letterSequence.getFirst() + " / " + letterSequence.getLast());

        // visit elements in reverse order
        System.out.println("Processing letterSequence in reverse order");
        SequencedCollection<String> reversed = letterSequence.reversed();
        reversed.forEach(System.out::print);
        System.out.println();
        System.out.println("reverse order stream skip 3");
        reversed.stream().skip(3).forEach(System.out::print);
        System.out.println();
        System.out.println(reversed.getFirst() + " / " + reversed.getLast());
        System.out.println();

        SequencedCollection<String> oneElement = new ArrayList<>(List.of("A"));
        System.out.println(oneElement.getFirst().equals(oneElement.getLast()));
        oneElement.addLast("Z");
        System.out.println(oneElement);

        //SequencedCollection<String> noElement = List.of();
        //noElement.getFirst();
        /*
        Exception in thread "main" java.util.NoSuchElementException
	at java.base/java.util.List.getFirst(List.java:825)
	at api.SequencedCollectionExample.sequenceCollectionExample(SequencedCollectionExample.java:47)
	at api.SequencedCollectionExample.main(SequencedCollectionExample.java:17)
         */

    }

    private static void modifiyOriginalAndView() {
        ArrayList<String> original = new ArrayList<>(List.of("B", "C", "D"));
        SequencedCollection<String> reversedView = original.reversed();

        original.addLast("EEE");
        reversedView.addLast("AAA");
        reversedView.addFirst("FFF");

        System.out.println(original);
        System.out.println(reversedView);
    }

    public static void sequenceSetExample() {
        // Plain Sets do not have encounter order ... run multiple time to see variation
        System.out.println("Processing set of letters A-D");
        Set.of("A", "B", "C", "D").forEach(System.out::print);
        System.out.println("\nProcessing set of letters A-G");
        Set.of("A", "B", "C", "D", "E", "F", "G").forEach(System.out::print);

        // TreeSet has order
        System.out.println("\nProcessing letterSequence with tree set");
        SequencedSet<String> sortedLetters = new TreeSet<>((Set.of("C", "B", "A", "D")));
        System.out.println(sortedLetters.getFirst() + " / " + sortedLetters.getLast());
        sortedLetters.reversed().forEach(System.out::print);
        System.out.println();
    }
}