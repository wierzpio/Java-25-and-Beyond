package ch10_api_java_22_25.jep484_class_file_api;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.CodeBuilder;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;

import static java.lang.classfile.ClassFile.ACC_PUBLIC;
import static java.lang.classfile.ClassFile.ACC_STATIC;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class HelloWorldClassFileBuilder {
    void main() throws IOException {
        ClassFile.of().buildTo(Path.of("HelloWorld.class"),
                ClassDesc.of("HelloWorld"),
                classBuilder -> createMain(classBuilder));
    }

    private static void createMain(ClassBuilder classBuilder) {
        classBuilder.withMethodBody("main",
                MethodTypeDesc.ofDescriptor("([Ljava/lang/String;)V"),
                ACC_PUBLIC | ACC_STATIC,
                codeBuilder -> createMainBodyCode(codeBuilder));
    }

    private static void createMainBodyCode(CodeBuilder codeBuilder) {
        codeBuilder.getstatic(ClassDesc.of("java.lang.System"), "out",
                        ClassDesc.of("java.io.PrintStream"))
                .ldc("Hello World From Class-File API")
                .invokevirtual(ClassDesc.of("java.io.PrintStream"),
                        "println",
                        MethodTypeDesc.ofDescriptor("(Ljava/lang/Object;)V"))
                .return_();
    }
}

