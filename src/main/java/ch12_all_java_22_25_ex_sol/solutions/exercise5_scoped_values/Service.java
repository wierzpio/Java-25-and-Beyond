package ch12_all_java_22_25_ex_sol.solutions.exercise5_scoped_values;

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
        var user = ScopedValuesExample.LOGGED_IN_USER.orElse(new User("n/a", "n/a"));

        System.out.println("Consumer: " + user);
    }

    public String process() {
        // wenn null, dann leider isBound() auch true
        if (ScopedValuesExample.LOGGED_IN_USER.isBound() && ScopedValuesExample.LOGGED_IN_USER.get() == null)
            throw new IllegalStateException("expected to be bound");

        if (ScopedValuesExample.LOGGED_IN_USER.get().name().startsWith("ADMIN"))
            return "ACCESS GRANTED!";
        else
            return "Top Secret Processing ... no access to ScopedValue granted";
    }

    public void processRequestOldStyle(final User loggedInUser) {
        System.out.println("processRequestOldStyle: " + loggedInUser);
    }
}
