package ch10_api_java_22_25.jep508_vector_api;

import jdk.incubator.vector.IntVector;

import java.util.Arrays;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class FirstVectorExample {
    public static void main(final String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8};

        var result1 = performScalarAddition(a, b);
        System.out.println("result using scalar calculation: " + Arrays.toString(result1));

        var result2 = performVectorAddition(a, b);
        System.out.println("result using Vector API: " + Arrays.toString(result2));
    }

    private static int[] performScalarAddition(int[] a, int[] b) {
        int[] result = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }

        return result;
    }

    private static int[] performVectorAddition(int[] a, int[] b) {
        int[] result = new int[a.length];

        IntVector vectorA = IntVector.fromArray(IntVector.SPECIES_256, a, 0);
        IntVector vectorB = IntVector.fromArray(IntVector.SPECIES_256, b, 0);

        IntVector vectorResult = vectorA.add(vectorB);
        // var vectorResult = vectorA.mul(vectorB);
        vectorResult.intoArray(result, 0);

        return result;
    }
}
