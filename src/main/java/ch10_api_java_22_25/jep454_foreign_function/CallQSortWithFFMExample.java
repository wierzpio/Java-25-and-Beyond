package ch10_api_java_22_25.jep454_foreign_function;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Comparator;
import java.util.List;

import static java.lang.foreign.Linker.nativeLinker;
import static java.lang.foreign.ValueLayout.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class CallQSortWithFFMExample {
    void main() throws Throwable {
        // Schritt 1 : SymbolLookUp + Method Handle ermitteln
        var qsortMethodHandle = getQSortMethodHandle();

        // Schritt 2:
        var arena = Arena.ofAuto();
        var callbackPtr = createCompareCallBack(arena);

        // Schritt 3: Eingabedaten
        int[] values = {9, 4, 8, 6, 11, 13, 17, 19, 7, 5, 3, 2};
        var valuesMemorySegment = arena.allocateFrom(JAVA_INT, values);

        // Schritt 4: Aufruf von "qsort" aus der C-Bibliothek
        qsortMethodHandle.invokeWithArguments(valuesMemorySegment,
                values.length,
                (int) JAVA_INT.byteSize(),
                callbackPtr);

        // Schritt 5: Rückkonvertierung aus dem sortierten C-Speicher
        // in eine Java-Liste
        List<Integer> sortedValues = convertToIntList(valuesMemorySegment);
        System.out.println("sortedValues: " + sortedValues);
    }

    private static MethodHandle getQSortMethodHandle() throws Exception {
        var cStdLib = nativeLinker().defaultLookup();
        var qsortSymbol = cStdLib.find("qsort").orElseThrow();

        // qsort erhält eine Adresse auf die Daten, zwei int sowie eine
        // Adresse auf eine Vergleichsfunktion für die Elemente
        var qsortFunc = FunctionDescriptor.ofVoid(ADDRESS,
                JAVA_INT, JAVA_INT,
                ADDRESS);

        return nativeLinker().downcallHandle(qsortSymbol, qsortFunc);
    }

    private static MemorySegment createCompareCallBack(final Arena arena)
            throws NoSuchMethodException, IllegalAccessException {
        var callbackJavaSig = MethodType.methodType(int.class, MemorySegment.class,
                MemorySegment.class);
        var callbackJava = MethodHandles.lookup().bind(new MyDescComparator(),
                "compare", callbackJavaSig);

        // Methodensignatur der Vergleichsfunktion in C
        var callbackCSig = FunctionDescriptor.of(JAVA_INT,
                ADDRESS,
                ADDRESS);

        // Java-Methode als Callback-Stub bereitstellen
        return nativeLinker().upcallStub(callbackJava, callbackCSig, arena);
    }

    static List<Integer> convertToIntList(MemorySegment valuesMemorySegment) {
        return valuesMemorySegment.elements(JAVA_INT)
                .map(element -> element.get(JAVA_INT, 0))
                .toList();
    }

    private static class MyDescComparator {
        private int compare(final MemorySegment left, final MemorySegment right) {
            var leftValue = extractValue(left);
            var rightValue = extractValue(right);

            return Integer.compare(rightValue, leftValue);
        }

        private static int extractValue(final MemorySegment memorySegment) {
            return MemorySegment.ofAddress(memorySegment.address()).
                    reinterpret(JAVA_INT.byteSize()).
                    get(JAVA_INT, 0);
        }
    }
}
