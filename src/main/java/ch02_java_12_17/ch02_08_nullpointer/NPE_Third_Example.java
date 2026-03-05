package ch02_java_12_17.ch02_08_nullpointer;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class NPE_Third_Example {
    public static void main(final String[] args) {
        int width = getWindowManager().getWindow(5).size().width();
        System.out.println("Width: " + width);
    }

    public static WindowManager getWindowManager() {
        return new WindowManager();
    }

    public static class WindowManager {
        public Window getWindow(final int i) {
            return null;
        }
    }

    public static record Window(Size size) {
    }

    public static record Size(int width, int height) {
    }
}