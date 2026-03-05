package ch04_syntax_java_18_21.ch04_02_pattern_matching;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SwitchCompletenessExample {
    // Demo: abstract wegnehmen
    static sealed abstract class BaseOp permits Add, Sub {
    }

    static final class Add extends BaseOp {
    }

    static final class Sub extends BaseOp {
    }

    static void performAction(BaseOp op) {
        switch (op) {
            case Add a -> System.out.println(a);
            case Sub s -> System.out.println(s);
            // default -> System.out.println("FALLBACK");
        }
    }

    static abstract sealed class BooleanExpression permits XORExpression, EqualsExpression {
        public void check(BooleanExpression bexp) {
            switch (bexp) {
                case XORExpression x -> System.out.println("XORExpression");
                case EqualsExpression e -> System.out.println("EqualsExpression");
                case null -> System.out.println("null");
                default -> System.out.println("default");
            }
        }
    }

    static final class XORExpression extends BooleanExpression {
    }

    static final class EqualsExpression extends BooleanExpression {
    }

    public static void main(final String[] args) {
        final BooleanExpression base = new EqualsExpression();
        base.check(new EqualsExpression());
        base.check(new XORExpression());
        base.check(null);
    }
}

