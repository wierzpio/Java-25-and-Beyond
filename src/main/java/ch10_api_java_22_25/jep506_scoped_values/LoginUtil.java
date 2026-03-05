package ch10_api_java_22_25.jep506_scoped_values;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class LoginUtil {
    private final XyzService service = new XyzService();

    public static final ScopedValue<User> LOGGED_IN_USER =
            ScopedValue.newInstance();

    // ...

    public void performLogin(final Request request) {
        User loggedInUser = extractUserFrom(request);
        ScopedValue.where(LOGGED_IN_USER,
                loggedInUser).run(() -> service.performAction());
    }

    private User extractUserFrom(Request request) {
        return new User("Peter", new char[]{'A', 'B', 'C', 'D', 'E'});
    }

    // ...
}