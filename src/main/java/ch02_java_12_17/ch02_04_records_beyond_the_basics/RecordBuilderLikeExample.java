package ch02_java_12_17.ch02_04_records_beyond_the_basics;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordBuilderLikeExample
{
    public static void main(final String[] args)
    {
		record SimplePerson(String name, int age, String city) 
		{			
			SimplePerson(String name)
			{
				this(name, 0, "");
			}
			
			SimplePerson(String name, int age)
			{
				this(name, age, "");
			}
			
			SimplePerson withAge(int newAge)
			{
				return new SimplePerson(name, newAge, city);
			}
			
			SimplePerson withCity(String newCity)
			{
				return new SimplePerson(name, age, newCity);
			}
		}
		
		SimplePerson mike = new SimplePerson("Mike", 51, "Zürich");
		SimplePerson mike2 = new SimplePerson("Mike", 35).withCity("Kiel");
		SimplePerson mike3 = new SimplePerson("Mike").withAge(42).withCity("Aachen");
		System.out.println(mike);
		System.out.println(mike2);
		System.out.println(mike3);
    }
}
