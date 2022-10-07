package com.lepesha.server.connection;

import com.lepesha.server.viewcontroller.MainViewController;
import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler extends Thread{
    private ServerSocket serverSocket;

    private MainViewController controller;

    private List<SingleClientThread> threadPool = new ArrayList<>();

    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (thread, ex) -> {
        Platform.runLater(() -> controller.addMessage(thread.getName() + " отключился!("));

        threadPool.remove(thread);

        System.out.println(ex);
    };

    public ServerHandler(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            i++;

            Socket newSocket = listenUser();
            SingleClientThread singleClientThread = new SingleClientThread(newSocket);
            singleClientThread.setName("Пользователь " + i);
            singleClientThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);

            Platform.runLater(() -> {
                controller.addMessage(singleClientThread.getName() + " подключился!");
            });

            threadPool.add(singleClientThread);

            singleClientThread.start();
        }
    }

    public Socket listenUser() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public void setController(MainViewController controller) {
        this.controller = controller;
    }
}
