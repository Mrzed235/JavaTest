package com.opencc.JAVANIO.javasocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static boolean socket_status = false;

    public static void main(String[] args) {
        connect();
    }

    public static void connect() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            socket_status = true;
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 输出流，向服务器发送消息
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

            new Thread(new Client_listen(socket, reader)).start();
            new Thread(new Client_send(socket, writer)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Client_send implements Runnable {
    private Socket socket;
    private OutputStreamWriter writer;

    public Client_send(Socket socket, OutputStreamWriter writer) {
        this.socket = socket;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("给服务端发消息，请输入:  ");
                String clientMessage = new BufferedReader(new InputStreamReader(System.in)).readLine();
                writer.write(clientMessage + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Client_listen implements Runnable {
    private Socket socket;
    private BufferedReader reader;

    public Client_listen(Socket socket, BufferedReader reader) {
        this.socket = socket;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("收到来自服务器消息: " + reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}