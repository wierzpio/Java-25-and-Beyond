package ch10_api_java_22_25.jep505_structured_concurrency;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NestedStructuredConcurrencyExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var city = "Zürich";
        var startDate = "2022-01-01";

        IO.println(performCityTripProposals(city, LocalDate.parse(startDate)));
    }

static Proposal performCityTripProposals(String city,
                                         LocalDate startDate) throws InterruptedException
{
      try (var scope = StructuredTaskScope.open()) {

        var hotelSubtask = scope.fork(() -> findHotels(city, startDate));
        var carRentalSubtask = scope.fork(() -> fetchCarRentals(city,
                                                                startDate));

        scope.join();

        return new Proposal(hotelSubtask.get(), carRentalSubtask.get());
    }
}

private static String findHotels(String city, LocalDate startDate) throws InterruptedException {
    var anySuccessful = StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow();
    try (var scope = StructuredTaskScope.open(anySuccessful)) {

        var proposal1 = scope.fork(() -> findProposalTrivago(city, startDate));
        var proposal2 = scope.fork(() -> findProposalBooking(city, startDate));
        var proposal3 = scope.fork(() -> findProposalCheck24(city, startDate));

        // Hier kommt der vielleicht Wunsch nach FirstNSuccessful auf,
        // falls man doch mehrere Vorschläge möchte
        return scope.join();
    }
}

    private static String findProposalBooking(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Booking Hotel";
    }

    private static String findProposalTrivago(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Trivago Hotel";
    }

    private static String findProposalCheck24(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Check24 Hotel";
    }

    private static List<String> fetchCarRentals(String city, LocalDate startDate) throws InterruptedException {
        var twoSuccessful = new OwnJoinersExample.FirstNSuccessful<String>(2);
        try (var scope = StructuredTaskScope.open(twoSuccessful)) {

            var proposal1 = scope.fork(() -> findProposalEuropcar(city, startDate));
            var proposal2 = scope.fork(() -> findProposalAvis(city, startDate));
            var proposal3 = scope.fork(() -> findProposalHertz(city, startDate));

            return scope.join();
        }
    }

    private static String findProposalEuropcar(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Europcar";
    }

    private static void randomlyWaitUpToOneSecond() {
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String findProposalAvis(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Avis";
    }

    private static String findProposalHertz(String city, LocalDate startDate) {
        randomlyWaitUpToOneSecond();
        return "Hertz";
    }
}
