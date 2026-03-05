/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
void main() throws IOException
{
    var name = IO.readln("Please enter your name: ");
    var age = Integer.parseInt(IO.readln("Please enter your age: "));
    var today = LocalDate.now();

    var info = """
            Hello, %s!
            Today is %s
            Your current age is %d
            """.formatted(name, today, age);
    IO.println(info);

    Path infoFile = Paths.get(name+".txt");
    Files.writeString(infoFile, info);

    String content = Files.readString(infoFile);
    Stream<String> asStream = content.lines();

    List<String> lines = asStream.collect(Collectors.toUnmodifiableList());
    IO.println(lines);
}