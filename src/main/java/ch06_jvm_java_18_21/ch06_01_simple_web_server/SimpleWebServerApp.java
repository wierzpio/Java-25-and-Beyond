package ch06_jvm_java_18_21.ch06_01_simple_web_server;

import java.net.InetSocketAddress;
import java.nio.file.Path;

import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class SimpleWebServerApp {
    public static void main(final String[] args) {
        var address = new InetSocketAddress(7777);
        // BITTE DEN PFAD AUF DEN EIGENEN ANPASSEN
        var path = Path.of("/Users/michaelinden");
        var verbosity = OutputLevel.VERBOSE;

        var server = SimpleFileServer.createFileServer(address, path, verbosity);

        server.start();

        System.out.println("SimpleWebServerApp is up and running");
    }
}