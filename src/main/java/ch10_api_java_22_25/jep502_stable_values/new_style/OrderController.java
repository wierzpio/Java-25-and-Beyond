package ch10_api_java_22_25.jep502_stable_values.new_style;

import java.util.List;
import java.util.function.Supplier;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
class OrderController {

    // OLD:
    // private final StableValue<Logger> logger = StableValue.of();
    //
    // Logger getLogger() {
    //     return logger.orElseSet(() -> Logger.create(OrderController.class));
    // }

    // NEW:
    private final Supplier<Logger> logger
        = StableValue.supplier(() -> Logger.create(OrderController.class));

    void submitOrder(User user, List<Product> products) {
        logger.get().info("order started");
        IO.println("order started");
        IO.println("User " + user);
        IO.println("Products " + products);
        logger.get().info("order submitted");
    }

}