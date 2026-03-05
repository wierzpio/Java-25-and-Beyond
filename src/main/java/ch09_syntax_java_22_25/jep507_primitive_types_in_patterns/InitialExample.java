package ch09_syntax_java_22_25.jep507_primitive_types_in_patterns;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class InitialExample {

    record LogLevel(int severity) {
    }

    public static void main(String[] args) {

        LogLevel logLevel = new LogLevel(2);

        handlelLogLevel(logLevel);

        // WIth JEP 455
        handleLogLevelJep455(logLevel);
    }

    private static void handlelLogLevel(LogLevel logLevel) {
        String msg = switch (logLevel.severity()) {
            case 0 -> "info";
            case 1 -> "warning";
            case 2 -> "error";
            default -> "unknown severity: " + logLevel.severity();
        };
        System.out.println(msg);
    }

    private static void handleLogLevelJep455(LogLevel logLevel) {
        String msg = switch (logLevel.severity()) {
            case 0 -> "info";
            case 1 -> "warning";
            case 2 -> "error";
            // JEP 455
            case int severity -> "unknown severity: " + severity;
        };
        System.out.println(msg);
    }
}
