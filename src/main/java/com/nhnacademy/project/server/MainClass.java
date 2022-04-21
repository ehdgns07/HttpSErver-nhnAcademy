package com.nhnacademy.project.server;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        ShttpServer server = new ShttpServer();
        server.serverToDo1();
    }
}
