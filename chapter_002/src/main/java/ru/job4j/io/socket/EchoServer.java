package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            int flag = 0;
            while (flag != 1) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    System.out.println(str);
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (str.contains("Exit")) {
                        flag = 1;
                    } else if (str.contains("Hello")) {
                        out.write("Hello".getBytes());
                    } else {
                        String s = str.substring((str.indexOf("=") + 1), str.lastIndexOf(" "));
                        out.write(s.getBytes());
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log: ", e);
        }
    }
}
