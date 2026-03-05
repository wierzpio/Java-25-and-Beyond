package ch10_api_java_22_25.jep506_scoped_values.advanced_example;

import java.time.ZonedDateTime;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class Controller {
    private final Service service;

    public Controller(final Service service) {
        this.service = service;
    }

    public void consumingMethod() {
        logRequestTime("consumingMethod()");
        service.consumingMethod();
    }

    public String process() {
        logRequestTime("process()");
        return service.process();
    }

    private static void logRequestTime(String methodName) {
        var requestTime = ScopedValuesExample.REQUEST_TIME;
        if (requestTime.isBound()) {
            System.out.println(methodName + " -- request time: " +
                    requestTime.get());
        }
    }
}