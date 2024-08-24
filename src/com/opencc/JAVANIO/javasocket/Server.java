package com.opencc.JAVANIO.javasocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("服务器启动，等待客户端连接...");
        try {
            try (ServerSocket serverSocket = new ServerSocket(8888)) {
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("客户端已连接");
                    new Thread(new Server_listen(socket)).start();
                    new Thread(new Server_send(socket)).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Server_listen implements Runnable {
    private Socket socket;

    public Server_listen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 输入流，接收客户端消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String clientMessage = reader.readLine();
                if (clientMessage == null) {
                    break;
                }
                System.out.println("收到来自客户端消息: " + clientMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Server_send implements Runnable {
    private Socket socket;

    public Server_send(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 输出流，向客户端发送消息
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            while (true) {
                System.out.println("给客户端发消息，请输入: ");
                String serverMessage = new BufferedReader(new InputStreamReader(System.in)).readLine();
                writer.write(serverMessage + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}