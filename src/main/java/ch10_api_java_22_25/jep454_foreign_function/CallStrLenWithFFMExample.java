package ch10_api_java_22_25.jep454_foreign_function;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;

import static java.lang.foreign.ValueLayout.*;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class CallStrLenWithFFMExample {
    void main() throws Throwable {
        // 1. SymbolLookup für gebräuchliche Bibliotheken ermitteln
        var linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();

        // 2. MethodHandle für "strlen" in der C Standard Library ermitteln
        var strlenMethodHandle = linker.downcallHandle(
                stdlib.find("strlen").orElseThrow(),
                FunctionDescriptor.of(JAVA_LONG, ADDRESS));

        // 3. Java in C String umwandeln und in C-Speicher bereitstellen
        Arena auto = Arena.ofAuto();
        var text = "direct c call of strlen";
        var strMemorySegment = auto.allocateFrom(text);

        // 4. Aufruf der "foreign function"
        long len = (long) strlenMethodHandle.invoke(strMemorySegment);

        System.out.println("len('" + text + "') = " + len);
        // 5. Speicher wird durch "Arena.ofAuto()" automatisch aufgeräumt
    }
}