package ch02_java_12_17.ch02_01_textblocks;

import java.time.LocalDate;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class TextBlocksExample {
    public static void main(final String[] args) {
        System.out.println("""
                  -Ich bin ein-
                  -Text Block-
                """);

        var firstTextBlockNoIndentation = """
                -Ich bin ein-
                -Text Block-""";
        System.out.println(firstTextBlockNoIndentation);

        var helloWorldHtml = """
                <html>
                    <body>
                        <p>Hello World</p>
                    </body>
                </html>""";
        System.out.println(helloWorldHtml);

        String jsonString = """
                     {
                        "name": "Mike",
                        "birthday": "1971-02-07",
                        "comment": "Text blocks are nice!"
                     }
                """;
        System.out.println(jsonString);

        String jsonTextBlock = """
                {
                    "version": "Java17 LTS",
                    "feature": "text blocks",
                    "attention": "very cool!"
                }""";
        System.out.println(jsonTextBlock);

        String placeholders = """
                Michael %s hat am "%tF"
                %d Bücher in '%s' gekauft.
                """.formatted("Inden", LocalDate.of(2020, 1, 20), 7, "Bremen");
        System.out.println(placeholders);

        String xmlString = """
                <customer>
                   <firstname>%s</firstname>
                   <lastname>%s</lastname>
                   <birthday>%s</birthday>
                </customer>
                """.formatted("Michael", "Inden", "07.02.1971");

        System.out.println(xmlString);

        String text = """
                This is a string splitted in several smaller \
                jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj \
                jjjjjjjjjjjjjjjjjjjjjjjj \
                jjjjjjjjjjjj strings""";

        System.out.println(text);
        System.out.println(text.getClass());
    }
}