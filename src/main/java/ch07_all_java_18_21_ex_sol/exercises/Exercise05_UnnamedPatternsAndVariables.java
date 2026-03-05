package ch07_all_java_18_21_ex_sol.exercises;


import java.time.Duration;
import java.time.LocalDate;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
// Okay for legacy code
// BUT: Better design would be to move the whole thing to Journey!!!
public class Exercise05_UnnamedPatternsAndVariables {

    public static void main(String[] args) {
        var michael = new Person("Michael", "Inden", LocalDate.of(1971, 2, 7));
        var travelInfo = new TravelInfo(LocalDate.now(), Duration.ofHours(5));
        var travelInfo2 = new TravelInfo(LocalDate.now(), Duration.ofHours(27));
        var zurich = new City(8047, "Zürich");
        var stuhr = new City(28816, "Stuhr");

        var journey = new Journey(michael, travelInfo, null, zurich);
        var journey2 = new Journey(michael, travelInfo2, null, zurich);
        var journey3 = new Journey(michael, travelInfo, null, stuhr);

        System.out.println(checkFirstNameAndCountryCodeAgain(journey));
        System.out.println(checkFirstNameAndCountryCodeAgainImproved(journey));
        System.out.println(checkFirstNameAndCountryCodeAgain(journey2));
        System.out.println(checkFirstNameAndCountryCodeAgainImproved(journey2));
        System.out.println(checkFirstNameAndCountryCodeAgain(journey3));
        System.out.println(checkFirstNameAndCountryCodeAgainImproved(journey3));
    }


    static boolean checkFirstNameAndCountryCodeAgain(Object obj) {
        if (obj instanceof Journey(
                Person(String firstname, String lastname, LocalDate birthday),
                TravelInfo(LocalDate start, Duration maxTravellingTime),
                City from, City(Integer zipCode, String name)
        )) {

            if (firstname != null && maxTravellingTime != null && zipCode != null) {

                return firstname.length() > 2 && maxTravellingTime.toHours() < 7 && zipCode >= 8000 && zipCode < 8100;
            }
        }
        return false;
    }

    static boolean checkFirstNameAndCountryCodeAgainImproved(Object obj) {
        if (obj instanceof Journey(
                Person(var firstname, var lastname, var birthday),
                TravelInfo(var start, var maxTravellingTime), var from,
                City(var zipCode, var name)
        )) {

            if (firstname != null && maxTravellingTime != null && zipCode != null) {

                return firstname.length() > 2 && maxTravellingTime.toHours() < 7 && zipCode >= 8000 && zipCode < 8100;
            }
        }
        return false;
    }
}
