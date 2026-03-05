package ch02_java_12_17.ch02_06_strings;

import java.util.Arrays;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class StringTransformExample
{
    public static void main(final String[] args)
    {
        transformationOldStyle();
        transformationJdk12Style();
    }

    private static void transformationOldStyle()
    {
        var text = "This is a Test";

        var upperCase = text.toUpperCase();
        var withoutTs = upperCase.replaceAll("T", "");
        var result = withoutTs.split(" ");

        System.out.println(Arrays.toString(result));
    }

    private static void transformationJdk12Style()
    {
        var text = "This is a Test";

        // Hintereinanderschaltung von Operationen
        var result = text.transform(String::toUpperCase).transform(str -> str.replaceAll("T", ""))
                        .transform(str -> str.split(" "));

        System.out.println(Arrays.toString(result));
    }
}
