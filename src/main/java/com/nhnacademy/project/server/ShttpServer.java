package com.nhnacademy.project.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShttpServer {
    private DataInputStream in;
    private DataOutputStream out;
    Map<String, Object> header = new HashMap<>();
    String path;
    String method;
    private String hostIp;

    public void serverToDo1() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(80)) {
            Socket socket = serverSocket.accept();
            System.out.println("클라이언트 연결1됨.");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            String msg;
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            int i =0;
            String[] test = new String[4];
            while(!Objects.equals(msg = read.readLine(), "")) {
                test[i] = msg;
                i++;
            }

            path = test[0].split(" ")[1];

            if(path.equals("/ip")) {
                sendIpPath(out);
            }
            if(path.equals("/get")) {
                sendIpPath(out);
            }
        }
    }

    private void sendIpPath(DataOutputStream out) throws IOException {
        this.out.writeBytes("HTTP/1.1" + " 200 OK\n");
        this.out.writeBytes("Date: " + LocalDateTime.now() +"\n");
        this.out.writeBytes("Content-Type: application/json\n");
        this.out.writeBytes("Content-Length: 33\n");
        this.out.writeBytes("Connection: keep-alive\n");
        this.out.writeBytes("Server: gunicorn/19.9.0\n");
        this.out.writeBytes("Access-Control-Allow-Origin: *\n");
        this.out.writeBytes("Access-Control-Allow-Credentials: true\n");
        this.out.writeBytes("\n");
        this.out.writeBytes("{\n");
        out.writeBytes("  \"origin\": "+ "bcd[1]" + "\n");
        out.writeBytes("}\n");
    }

    private void recieveGet(DataOutputStream out) throws IOException {
        out.writeUTF("test");
   /*     for ( out : out.) {
            try {
                out.writeUTF("test");
            } catch (IOException e) {
                // TODO: 해당 클라이언트로 송출 스트림이 실패함(네트워크 끈김)
            }
        }*/
    }
}

