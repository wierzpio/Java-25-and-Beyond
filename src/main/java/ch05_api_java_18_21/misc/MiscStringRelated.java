package ch05_api_java_18_21.misc;

import java.util.Arrays;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class MiscStringRelated {
    public static void main(final String[] args) {
        var sb = new StringBuffer();
        sb.repeat('*', 10);
        System.out.println(sb.toString());

        var tomAndJerry = "tom:and::jerry:::news---ticker---top";

        String[] splits = tomAndJerry.splitWithDelimiters(":+", 3);
        System.out.println(Arrays.toString(splits));

        // limit bezieht sich auf die Matches, Delimiter zählen nicht mit
        String[] splits2 = tomAndJerry.splitWithDelimiters("(:+|-{3})", 4);
        System.out.println(Arrays.toString(splits2));

        String[] splits3 = tomAndJerry.splitWithDelimiters("(:+|-{3})", 5);
        System.out.println(Arrays.toString(splits3));

        String[] splits4 = tomAndJerry.splitWithDelimiters("(:+|-{3})", 6);
        System.out.println(Arrays.toString(splits4));
    }
}
