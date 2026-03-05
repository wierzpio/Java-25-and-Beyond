package ch02_java_12_17.ch02_04_records_beyond_the_basics;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordSerialisationExample {
    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES);

        //var person = new Person("John", "Doe", "USA", LocalDate.of(1991, 3, 29), List.of("Speaker"));
        var person = new Person("John", "Doe", "USA", new Date(), List.of("Speaker"));
        //var person = new Person("John", "Doe", "USA", new Date(), List.of("Speaker"));

        var serializedJson = mapper.writeValueAsString(person);
        System.out.println(serializedJson);

        // Achtung: Java 8 date/time type `java.time.LocalDate` not supported by default
        var person2 = new Person2("John", "Doe", "USA", LocalDate.of(1991, 3, 29), List.of("Speaker"));

        // support Java 8 date time apis
        // mapper.registerModule(new JavaTimeModule());

        var serializedJson2 = mapper.writeValueAsString(person2);
        System.out.println(serializedJson2);
    }
}
