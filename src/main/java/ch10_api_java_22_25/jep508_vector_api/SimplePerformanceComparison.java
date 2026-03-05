package ch10_api_java_22_25.jep508_vector_api;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.stream.DoubleStream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SimplePerformanceComparison {
    public static void main(final String[] args) {
        // 1_000_000 => scalar faster
        for (int size : new int[]{1_000, 1_000_000, 100_000_000, 500_000_000}) {

            System.out.println("Measuring size: " + size);
            System.out.println("---------------------------");

            double[] values1 = DoubleStream.iterate(1, i -> i + 1).limit(size).toArray();
            double[] values2 = DoubleStream.iterate(1, i -> i + 1).limit(size).toArray();

            for (int i = 0; i < 20; i++) {
                double[] result1 = measureScalar(i, values1, values2);
            }

            for (int i = 0; i < 20; i++) {
                double[] result2 = measureVector(i, values1, values2);
            }
        }
    }

    static private double[] measureScalar(int iteration, double[] input1, double[] input2) {
        double[] output = new double[input1.length];
        long start = System.nanoTime();
        for (int i = 0; i < input1.length; ++i) {
            output[i] = Math.sqrt(input1[i] * input1[i] + input2[i] + input2[i]) +
                    Math.sqrt(input1[i] * input1[i] - input2[i] + input2[i]);
        }
        long finish = System.nanoTime();
        System.out.println(iteration + "\t scalar duration (ns)\t" + (finish - start));
        return output;
    }

    static private double[] measureVector(int iteration, double[] input1, double[] input2) {
        final VectorSpecies<Double> SPECIES = DoubleVector.SPECIES_PREFERRED;

        double[] output = new double[input1.length];
        long start = System.nanoTime();
        for (int i = 0; i < SPECIES.loopBound(input1.length); i += SPECIES.length()) {
            DoubleVector va = DoubleVector.fromArray(SPECIES, input1, i);
            DoubleVector vb = DoubleVector.fromArray(SPECIES, input2, i);
            DoubleVector vc = va.mul(va).add(vb.mul(vb)).sqrt().add(va.mul(va).sub(vb.mul(vb)).sqrt());
            vc.intoArray(output, i);
        }
        long finish = System.nanoTime();
        System.out.println(iteration + "\t vector duration (ns)\t" + (finish - start));
        return output;
    }
}
