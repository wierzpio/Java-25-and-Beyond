package ch10_api_java_22_25.jep506_scoped_values.advanced_example;

import java.io.IOException;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ExceptionHandlingExample {
    public static final ScopedValue<User> LOGGED_IN_USER =
            ScopedValue.newInstance();

    void main() {
        var user = new User("Tim", "Bötz");

        try {
            var result = ScopedValue.where(ExceptionHandlingExample.LOGGED_IN_USER, user).
                    call(() -> ExceptionHandlingExample.performCalculation());
            System.out.println("Calculated result: " + result);
            throw new IOException();
        } catch (IOException ioe) {
            handleIoException(ioe);
        }

        var result = ScopedValue.where(ExceptionHandlingExample.LOGGED_IN_USER, user).
                call(() -> ExceptionHandlingExample.performCalculationUnchecked());
        System.out.println("Calculated result: " + result);
    }

    static String performCalculation() throws IOException {
        if (ExceptionHandlingExample.LOGGED_IN_USER.isBound())
            return ExceptionHandlingExample.LOGGED_IN_USER.get().name();

        return "FALLBACK FIRST";
    }

    static String performCalculationUnchecked() throws IllegalStateException {
        if (ExceptionHandlingExample.LOGGED_IN_USER.isBound())
            return ExceptionHandlingExample.LOGGED_IN_USER.get().nickName();

        return "FALLBACK SECOND";
    }

    private static void handleIoException(IOException ioe) {
        System.out.println("Caught exception: " + ioe);
    }
}
