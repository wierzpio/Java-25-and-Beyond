package appendix_java_8_11.a1_lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ComparatorIntroExample
{
    public static void main(final String[] args)
    {
        final List<String> names = Arrays.asList("Peter", "Tim", "Andy", "Michael", "Stefan");

        final Comparator<String> compareByLength = (str1, str2) ->
                        Integer.compare(str1.length(), str2.length());

        names.sort(compareByLength);
        System.out.println(names);
    }
}
