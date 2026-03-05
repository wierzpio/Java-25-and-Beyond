package ch02_java_12_17.ch02_03_records;

import java.util.Objects;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public record RecordEqualsExample(String name, int age) {
    // ACHTUNG: DO NOT DO IT AT HOME :-)

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecordEqualsExample other = (RecordEqualsExample) obj;
        return Objects.equals(name, other.name);
    }

    public static void main(String[] args) {
        var p1 = new RecordEqualsExample("Peter", 72);
        var p2 = new RecordEqualsExample("Peter", 27);

        System.out.println(p1.equals(p2));
    }
}
