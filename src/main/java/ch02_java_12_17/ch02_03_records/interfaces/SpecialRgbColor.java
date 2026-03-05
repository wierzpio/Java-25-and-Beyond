package ch02_java_12_17.ch02_03_records.interfaces;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
/*
record SpecialRgbColor(String rgb) implements RgbAccess
{
    public int red()
    {
        return Integer.parseInt(rgb.split(",")[0].strip());
    }
 
    public int green()
    {
        return Integer.parseInt(rgb.split(",")[1].strip());
    }
     
    public int blue()
    {
        return Integer.parseInt(rgb.split(",")[2].strip());
    }
}
*/
record SpecialRgbColor(String rgb) implements RgbAccess
{
    public int red()
    {
        return parseColorComponent(0);
    }

    public int green()
    {
        return parseColorComponent(1);
    }

    public int blue()
    {
        return parseColorComponent(2);
    }

    private int parseColorComponent(int index)
    {
        return Integer.parseInt(rgb.split(",")[index].strip());
    }
}