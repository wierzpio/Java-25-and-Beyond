package ch10_api_java_22_25.jep485_stream_gatherers.bonus;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bricht den Scope ab, sobald mehr als n Fehler aufgetreten sind.
 * Erfolgreiche Ergebnisse werden gesammelt und als List<T> zurückgegeben.
 */
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
