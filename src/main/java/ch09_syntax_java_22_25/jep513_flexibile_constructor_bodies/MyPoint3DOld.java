package ch09_syntax_java_22_25.jep513_flexibile_constructor_bodies;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
record MyPoint3DOld(int x, int y, int zy) {
    public MyPoint3DOld(final String values) {
        this(Integer.parseInt(values.split(",")[0].strip()),
                Integer.parseInt(values.split(",")[1].strip()),
                Integer.parseInt(values.split(",")[2].strip()));
    }

    public static void main(String[] args) {
        System.out.println(new MyPoint3DOld(1, 2, 3));
        System.out.println(new MyPoint3DOld("1,2,3"));
        System.out.println(new MyPoint3DOld("1, 2, 3"));
    }
}