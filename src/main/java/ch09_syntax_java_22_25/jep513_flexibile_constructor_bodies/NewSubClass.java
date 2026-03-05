package ch09_syntax_java_22_25.jep513_flexibile_constructor_bodies;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NewSubClass extends BaseClass {

    private final String subClassInfo;

    public NewSubClass(int baseValue, String subClassInfo) {
        // Zuweisung an Attribut dieser Klasse
        // Assignment to attribute of this class
        this.subClassInfo = subClassInfo;

        // vor dem Aufruf an super()
        // before the call to super()
        super(baseValue);
    }

    protected void logValues() {
        super.logValues();
        System.out.println("subClassInfo: " + subClassInfo);
    }

    public static void main(final String[] args) {
        new NewSubClass(42, "AS_EXPECTED");
    }
}
