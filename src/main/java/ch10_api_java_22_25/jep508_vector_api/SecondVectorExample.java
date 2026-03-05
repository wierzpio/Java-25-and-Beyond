package ch10_api_java_22_25.jep508_vector_api;

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
public class SecondVectorExample {
    public static void main(final String[] args) {
        final float[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final float[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        final float[] result1 = scalarComputation(a, b);
        System.out.println("result using scalar calculation: " +
                Arrays.toString(result1));

        final float[] result2 = vectorComputation(a, b);
        System.out.println("result using Vector API: " + Arrays.toString(result2));
    }

    static float[] scalarComputation(float[] a, float[] b) {
        float[] result = new float[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }

        return result;
    }

    static float[] vectorComputation(float[] a, float[] b) {
        final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
        final float[] result = new float[a.length];

        int i = 0;
        for (; i < SPECIES.loopBound(a.length); i += SPECIES.length()) {
            var vectorA = FloatVector.fromArray(SPECIES, a, i);
            var vectorB = FloatVector.fromArray(SPECIES, b, i);
            var vectorResult = vectorA.mul(vectorA).
                    add(vectorB.mul(vectorB)).neg();

            vectorResult.intoArray(result, i);
        }
        for (; i < a.length; i++) {
            result[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }

        return result;
    }
}
