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
public record ComplexPerson(String firstname, String surname, LocalDate birthday, int height, int weight,
                            String addressStreet, String addressNumber, String city)
{
    public static void main(String[] args)
    {
        ComplexPerson john = new ComplexPerson("John", "Smith", LocalDate.of(2010, 6,  21), 
                                               170, 90, "ullstrasse", "16a", "Berlin"); 
        
        ComplexPerson mike = new ComplexPersonBuilder().withFirstName("Mike").
                        withBirthday(LocalDate.of(2021, 1, 21)).withSurName("Peters").build();
        System.out.println(mike);          
    }   
}

// naja, Handarbeit
class ComplexPersonBuilder
{
    private String firstname;
    private String surname;
    private LocalDate birthday;
    
    public ComplexPersonBuilder()
    {}
    
    public ComplexPersonBuilder withFirstName(String firstname)
    {
        this.firstname = firstname;
        return this;
    }
    
    public ComplexPersonBuilder withSurName(String suname)
    {
        this.surname = suname;
        return this;
    }
    
    public ComplexPersonBuilder withBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
        return this;
    }
    
    public ComplexPerson build()
    {
        return new ComplexPerson(firstname, surname, birthday, 0, 0, "", "", "");
    }
}


