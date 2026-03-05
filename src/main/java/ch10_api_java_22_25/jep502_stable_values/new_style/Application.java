package ch10_api_java_22_25.jep502_stable_values.new_style;

import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
class Application {

    // OLD:
    // static final OrderController   ORDERS   = new OrderController();
    // static final ProductRepository PRODUCTS = new ProductRepository();
    // static final UserService       USERS    = new UserService();

    // NEW:
    static final StableValue<OrderController>   ORDERS   = StableValue.of();
    static final StableValue<ProductRepository> PRODUCTS = StableValue.of();
    static final StableValue<UserService>       USERS    = StableValue.of();

    public static OrderController orders() {
        return ORDERS.orElseSet(OrderController::new);
    }

    public static ProductRepository products() {
        return PRODUCTS.orElseSet(ProductRepository::new);
    }

    public static UserService users() {
        return USERS.orElseSet(UserService::new);
    }


    public static void main(String[] args) {
        orders().submitOrder(new User("Peter", "Zürich"), List.of(new Product("IPhone"), new Product("Pizza Napoli")));
    }
}