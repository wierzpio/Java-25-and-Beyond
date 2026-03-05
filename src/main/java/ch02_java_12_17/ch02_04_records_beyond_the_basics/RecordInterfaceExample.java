package ch02_java_12_17.ch02_04_records_beyond_the_basics;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordInterfaceExample
{
    public static void main(String[] args) throws JsonProcessingException
    {
		record SimplePerson(String name, int age, String city) {}
		
		Set<SimplePerson> speakers = new HashSet<>();
		speakers.add(new SimplePerson("Michael", 51, "Zürich"));
		speakers.add(new SimplePerson("Michael", 51, "Zürich"));
		speakers.add(new SimplePerson("Anton", 42, "Aachen"));
		
		System.out.println(speakers);

        // -------------------
        
		Set<SimplePerson> sortedSpeakers = new TreeSet<>();
		sortedSpeakers.add(new SimplePerson("Michael", 51, "Zürich"));
		sortedSpeakers.add(new SimplePerson("Michael", 51, "Zürich"));
		sortedSpeakers.add(new SimplePerson("Anton", 42, "Aachen"));
		   
		System.out.println(sortedSpeakers);
    }
}
