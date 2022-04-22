package com.nhnacademy.project.server;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MainClass {
    public static void main(String[] args) throws IOException {
        ShttpServer server = new ShttpServer();

        server.serverToDo1();
    }
}
