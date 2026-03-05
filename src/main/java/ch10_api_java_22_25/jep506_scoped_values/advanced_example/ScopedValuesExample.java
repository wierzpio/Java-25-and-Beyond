package ch10_api_java_22_25.jep506_scoped_values.advanced_example;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ScopedValuesExample {
    public static final ScopedValue<User> LOGGED_IN_USER =
            ScopedValue.newInstance();
    public static final ScopedValue<ZonedDateTime> REQUEST_TIME =
            ScopedValue.newInstance();

    public static final Controller controller =
            createController(new Service());

    private static Controller createController(final Service service) {
        return new Controller(service);
    }

    // ...
    void main() throws Exception {
        // Simulate Requests
        for (String name : List.of("ATTACKER", "ADMIN")) {
            var user = new User(name, name.toLowerCase());
            ScopedValue.where(LOGGED_IN_USER, user).
                    where(REQUEST_TIME, ZonedDateTime.now()).
                    run(controller::consumingMethod);

            // kein Shortcut mehr: where() + run(), aber nur eine Belegung
            ScopedValue.where(LOGGED_IN_USER, user).
                    run(controller::consumingMethod);

            String answer = ScopedValue.where(LOGGED_IN_USER, user).
                    call(() -> controller.process());
            System.out.println(answer);
        }

        // Außerhalb des Scopes ist die Variable "unbound"
        System.out.print("Outside bounded scope ");
        System.out.println("isBound(): " + LOGGED_IN_USER.isBound());
    }
}