package com.nhnacademy.project.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ShttpServer {
    private DataInputStream in;
    private DataOutputStream out;
    Map<String, Object> header = new HashMap<>();
    String path;
    String method;
    String httpVersion;
    private String hostIp;

    public void serverToDo1() throws IOException {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(80);
                 Socket socket = serverSocket.accept()) {

                System.out.println("클라이언트 연결1됨.");
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                String msg;
                BufferedReader read = new BufferedReader(new InputStreamReader(in));

                int i = 0;
                String[] test = new String[4];
                while (!Objects.equals(msg = read.readLine(), "")) {
                    test[i] = msg;
                    i++;
                }

                path = test[0].split(" ")[1];
                httpVersion = test[0].split(" ")[2];
                InetAddress ip = socket.getInetAddress();

                if (path.equals("/ip")) {
                    sendIpPath(out, httpVersion, ip, path);
//                    break;
                }
                if (path.equals("/get")) {
//                sendIpPath(out);
                }
            }
        }
    }

    private void sendIpPath(DataOutputStream out, String httpVersion, InetAddress ip, String path) throws IOException {
        String origin = "  \"origin\": "+ "\"" + ip +"\"" + "\n";
        out.writeBytes(httpVersion + " 200 OK\n");
        out.writeBytes("Content-Type: application/json\n");
        out.writeBytes("Date: " + datePattern() +" GMT\n");
        out.writeBytes("Content-Length: "+ origin.length() + "\n");
        out.writeBytes("Connection: keep-alive\n");
        out.writeBytes("Server: team 3/19.9.0\n");
        out.writeBytes("Access-Control-Allow-Origin: *\n");
        out.writeBytes("Access-Control-Allow-Credentials: true\n");
        out.writeBytes(" \n");
        out.writeBytes("{\n");
        out.writeBytes(origin);
        out.writeBytes("}\n");
    }

    private void recieveGet(DataOutputStream out) throws IOException {

   /*     for ( out : out.) {
            try {
                out.writeUTF("test");
            } catch (IOException e) {
                // TODO: 해당 클라이언트로 송출 스트림이 실패함(네트워크 끈김)
            }
        }*/
    }

    public String datePattern(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, dd M yyyy hh:mm:ss ").withLocale(
            Locale.ENGLISH));
    }
}

