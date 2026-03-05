package ch12_all_java_22_25_ex_sol.exercises.exercise9_flexible_ctor_bodies;

import java.util.Arrays;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
class PlainByteMsg {

    private final byte[] payload;

    public PlainByteMsg(final byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PlainByteMsg{" +
                "payload=" + Arrays.toString(payload) +
                '}';
    }
}