package ch02_java_12_17.ch02_06_strings;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class StringIndentExample
{
    public static void main(final String[] args)
    {
        var test = "Test".indent(4);
        printTextAndLength(test);

        var removeTest = "     6789".indent(-5);
        printTextAndLength(removeTest);
    }

    private static void printTextAndLength(final String str)
    {
        System.out.println("'" + str + "'");
        System.out.println(str.length());
    }

    private static void indentProblem()
    {
        var header = "Level 0: Report";
        var infoMessage = "Level 1: This is a message".indent(4);
        var infoMessage2 = "Level 2: This is a line 2".indent(8);

        System.out.println(header);
        System.out.println(infoMessage);
        System.out.println(infoMessage2);
    }

    private static void indentHowItShouldBeUsed()
    {
        var header = "Report";
        var infoMessage = "This is a message\nThis is line 2\nLine3".indent(4);

        System.out.println(header);
        System.out.println(infoMessage);
    }
}
