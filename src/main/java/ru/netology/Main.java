package ru.netology;

import java.io.*;

public class Main {
    public static final int PORT = 8080;
    public static final int POOL_SIZE = 64;

    public static void main(String[] args) {
        Server server = new Server(POOL_SIZE);

        server.addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream, "200", "OK");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        server.addHandler("POST", "/messages", ((request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream, "500", "Service Unavailable");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        server.addHandler("GET", "/", ((request, outputStream) -> {
            try {
                server.defaultHandler(outputStream, "index.html");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        server.start(PORT);
    }

}