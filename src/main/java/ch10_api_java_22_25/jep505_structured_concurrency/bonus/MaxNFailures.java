package ch10_api_java_22_25.jep505_structured_concurrency.bonus;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
///
/// Bricht den Scope ab, sobald mehr als n Fehler aufgetreten sind.
/// Erfolgreiche Ergebnisse werden gesammelt und als 'List<T>' zurückgegeben.
///
public final class MaxNFailures<T> implements StructuredTaskScope.Joiner<T, List<T>> {
    private final int maxFailures; // Abbruchschwelle: "mehr als n"
    private final AtomicInteger failures = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<T> successes = new ConcurrentLinkedQueue<>();

    public MaxNFailures(int maxFailures) {
        if (maxFailures < 0) throw new IllegalArgumentException("maxFailures must be >= 0");
        this.maxFailures = maxFailures;
    }

    @Override
    public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
        switch (subtask.state()) {
            case SUCCESS -> {
                T v = subtask.get();
                if (v != null) successes.add(v);
                return false; // kein Short-Circuit wegen Erfolg
            }
            case FAILED -> {
                int f = failures.incrementAndGet();
                return f > maxFailures; // nach mehr als n Fehlern abbrechen
            }
            default -> { // CANCELLED (z.B. weil Scope schon abgebrochen wurde)
                return false;
            }
        }
    }

    @Override
    public List<T> result() {
        return List.copyOf(successes);
    }
}
