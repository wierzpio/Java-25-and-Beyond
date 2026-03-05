package ch12_all_java_22_25_ex_sol.exercises.exercise7_vector_api;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.Arrays;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class VectorApi {
    void main() {
        float[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        float[] b = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        float[] result1 = scalarComputation(a, b);
        System.out.println("result using scalar calulation: " + Arrays.toString(result1));

        float[] result2 = vectorComputation(a, b);
        System.out.println("result using Vector API: " + Arrays.toString(result2));
    }

    static float[] scalarComputation(float[] a, float[] b) {
        float[] c = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i] + a[i] * b[i] - (a[i] + b[i]);
        }

        return c;
    }

    static float[] vectorComputation(float[] a, float[] b) {
        final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
        float[] c = new float[a.length];

        // TODO

        return c;
    }
}