package ch02_java_12_17.ch02_04_records_beyond_the_basics;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class RecordJSONExample {
    record MyJsonPerson(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            String address,
            Date birthday,
            List<String> achievements) {
    }

    // Validierung
    public record ItemAsRecord(
            /*@Positive*/ @JsonProperty("id") int id,
            /*@NotBlank*/ @JsonProperty("name") String name,
            /*@Size(max = 5)*/ @JsonProperty("location") String location) {
    }


    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        var person = new Person(
                "John",
                "Doe",
                "USA",
                new Date(981291289182L),
                List.of("Speaker")
        );

        System.out.println(mapper.writeValueAsString(person));
    }
}
