package appendix_java_8_11.a3_misc;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class OptionalIfPresentOrElseExample {
    public static void main(final String[] args) {
        final Optional<String> optCustomer1 = findCustomer("Tim");
        optCustomer1.ifPresentOrElse(str -> System.out.println("found: " + str), () -> System.out.println("not found"));

        final Optional<String> optCustomer2 = findCustomer("UNKNWON");
        optCustomer2.ifPresentOrElse(str -> System.out.println("found: " + str), () -> System.out.println("not found"));
    }

    private static Optional<String> findCustomer(String customerId) {
        final Stream<String> customers = Stream.of("Tim", "Tom", "Mike", "Andy");
        if (customers.anyMatch(name -> name.contains(customerId))) {
            return Optional.of(customerId);
        }
        return Optional.empty();
    }
}
