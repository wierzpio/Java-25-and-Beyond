package ch10_api_java_22_25.jep506_scoped_values.advanced_example;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Service {
    public void consumingMethod() {
        var user = ScopedValuesExample.LOGGED_IN_USER.orElse(new User("n/a", ""));

        System.out.println("Consumer: " + user);
    }

    public String process() {
        if (!ScopedValuesExample.LOGGED_IN_USER.isBound())
            throw new IllegalStateException("expected user to be bound");

        // ACHTUNG: wenn null gebunden, dann liefert isBound() leider auch true
        if (ScopedValuesExample.LOGGED_IN_USER.get() == null)
            throw new IllegalStateException("expected to contain a value");

        var user = ScopedValuesExample.LOGGED_IN_USER.get();
        if (user.name().startsWith("ADMIN"))
            return "ACCESS GRANTED!";
        else
            return "Top Secret ... no access to Scoped Value granted";
    }
}