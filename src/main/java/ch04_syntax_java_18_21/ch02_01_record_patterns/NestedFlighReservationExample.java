package ch04_syntax_java_18_21.ch02_01_record_patterns;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NestedFlighReservationExample {
    record Person(String firstname, String lastname, LocalDate birthday) {
    }

    record Phone(String areaCode, String number) {
    }

    record City(String name, String country, String languageCode) {
    }

    record FlightReservation(Person person, Phone phoneNumber, City origin, City destination) {
    }

    boolean checkAgeAndDestinationLanguageCorrectOld(final Object obj) {
        if (obj instanceof FlightReservation reservation) {
            if (reservation.person() != null) {
                var person = reservation.person();
                var birthday = person.birthday();

                if (reservation.destination() != null) {
                    var destination = reservation.destination();
                    var languageCode = destination.languageCode();

                    if (birthday != null && languageCode != null) {
                        long years = ChronoUnit.YEARS.between(LocalDate.now(), birthday);
                        return years >= 18 && List.of("EN", "DE", "FR").contains(languageCode);
                    }
                }
            }
        }
        return false;
    }

    boolean checkAgeAndDestinationLanguageCorrectNew(final Object obj) {
        if (obj instanceof FlightReservation(
                Person(String firstname, String lastname, LocalDate birthday),
                Phone phoneNumber, City origin,
                City(String name, String country, String languageCode)
        )) {
            if (birthday != null && languageCode != null) {
                long years = ChronoUnit.YEARS.between(birthday, LocalDate.now());

                return years >= 18 && List.of("EN", "DE", "FR").contains(languageCode);
            }
        }
        return false;
    }

    boolean checkAgeAndDestinationLanguageCorrectNewV2(final Object obj) {
        if (obj instanceof FlightReservation(
                Person(var _dc1, String _dc2, LocalDate birthday),
                Phone _dc_phoneNumber, City _dc_origin,
                City(var name, var country, var languageCode)
        )) {
            if (birthday != null && languageCode != null) {
                long years = ChronoUnit.YEARS.between(birthday, LocalDate.now());

                return years >= 18 && List.of("EN", "DE", "FR").contains(languageCode);
            }
        }
        return false;
    }
}
