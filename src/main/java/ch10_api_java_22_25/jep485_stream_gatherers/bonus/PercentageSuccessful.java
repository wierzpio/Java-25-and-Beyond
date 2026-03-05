package ch10_api_java_22_25.jep485_stream_gatherers.bonus;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Beendet den Scope erfolgreich, sobald mindestens "percent" Prozent
 * der (geplanten) Subtasks erfolgreich sind; übrige Subtasks werden gecancelt.
 *
 * Hinweis: "totalTasks" sollte der Zahl der geforkten Subtasks entsprechen.
 */
public final class PercentageSuccessful<T> implements StructuredTaskScope.Joiner<T, List<T>> {
    private final int totalTasks;
    private final int requiredSuccesses; // ceil(percent/100 * totalTasks)

    private final AtomicInteger successCount = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<T> successes = new ConcurrentLinkedQueue<>();

    public PercentageSuccessful(int totalTasks, int percent) {
        if (totalTasks <= 0) throw new IllegalArgumentException("totalTasks must be > 0");
        if (percent < 1 || percent > 100) throw new IllegalArgumentException("percent must be in [1..100]");
        this.totalTasks = totalTasks;
        this.requiredSuccesses = (int) Math.ceil(percent / 100.0 * totalTasks);
    }

    @Override
    public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
        if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
            int slot = successCount.getAndIncrement();   // exklusiv für diesen Erfolg
            T v = subtask.get();
            if (v != null) successes.add(v);
            // Sobald die erforderliche Anzahl Erfolge erreicht ist -> vorzeitig beenden
            return slot + 1 >= requiredSuccesses;
        }
        // Fehler/CANCELLED zählen hier nicht zur Schwelle; Scope läuft weiter
        return false;
    }

    @Override
    public List<T> result() {
        // defensiv auf die geforderte Anzahl begrenzen (bei zeitgleichem Überschuss)
        return successes.stream().limit(requiredSuccesses).toList();
    }
}