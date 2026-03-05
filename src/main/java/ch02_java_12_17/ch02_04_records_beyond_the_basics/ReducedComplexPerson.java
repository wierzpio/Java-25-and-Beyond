package ch02_java_12_17.ch02_04_records_beyond_the_basics;

import java.time.LocalDate;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
record Address(String addressStreet, String addressNumber, String city) {
}

record BodyInfo(int height, int weight)
{
}

record PersonDTO(String firstname, String surname, LocalDate birthday)
{
}

public record ReducedComplexPerson(PersonDTO person, BodyInfo bodyInfo, Address address)
{
    public static void main(String[] args)
    {
        var john = new PersonDTO("“John", "Smith", LocalDate.of(2010, 6,  21));
        var bodyInfo = new BodyInfo(170, 90);
        var address = new Address("ullstrasse", "16a", "Berlin"); 

        var rcp = new ReducedComplexPerson(john, bodyInfo, address);
        System.out.println(rcp);
    }
}