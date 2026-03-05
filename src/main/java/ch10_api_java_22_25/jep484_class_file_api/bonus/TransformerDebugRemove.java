package ch10_api_java_22_25.jep484_class_file_api.bonus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;
import java.nio.file.Path;

/*
Nehmen wir an, wir wollen aus dem Bytecode, also der Klassendatei, alle Methoden entfernen,
die mit dem Startkürzel \code{debug} gekennzeichnet sind. Alles andere soll unverändert bleiben.
Basierend auf den Bytes der Klassendatei wird ein \code{ClassModel} erzeugt. Dieses können
wir über die Methode \code{build()} wird ein \code{ClassBuilder} erzeugt und
es erfolgt die Angabe einer Transformationsvorschrift für die vorzunehmenden Modifikationen.
Dazu durchläuft man alle Elemente vom Typ \code{ClassElement} des \code{ClassModel} und
prüft auf Debug-Methoden und gibt alles andere an den \code{ClassBuilder} weiter:

Aber Achtung: Es werden nur die Methodendefinitionen entfernt, potenzielle Aufrufe nicht!
 */

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class TransformerDebugRemove {
    void main() throws IOException {

        ClassFile cf = ClassFile.of();
        ClassModel classModel = cf.parse(Path.of("target/classes/jep457_Class_File_Api/bonus/ExampleDebug.class"));
        byte[] newBytes = cf.build(classModel.thisClass().asSymbol(),
                classBuilder -> {
                    for (ClassElement ce : classModel) {

                        // Step 1:
                        if (!isDebugMethod(ce)) {
                            classBuilder.with(ce);
                        }

                        // Step 2: Problem das ist bestandteil vom CodeModel
                        /*
                        if (!isDebugMethodInvocation(ce)) {
                            classBuilder.with(ce);
                        }
                        */
                    }
                });

        // write without debug methode
        new File("jep457_Class_File_Api_Modified").mkdir();
        try (var fos = new FileOutputStream("jep457_Class_File_Api_Modified/ExampleDebug.class")) {
            fos.write(newBytes);
        }
    }

    private static boolean isDebugMethod(ClassElement ce) {
        return ce instanceof MethodModel methodModel &&
                methodModel.methodName().stringValue().startsWith("debug");
    }

    /*private static boolean isDebugMethodInvocation(ClassElement ce) {
        return ce instanceof InvokeInstruction ii && ii.method().name().stringValue().startsWith("debug");
    }
     */
}
