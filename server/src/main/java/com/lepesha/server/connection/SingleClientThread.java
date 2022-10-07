package com.lepesha.server.connection;

import com.lepesha.server.calcResult.CalculationResult;
import com.lepesha.server.parse.Parser;
import javafx.scene.Parent;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SingleClientThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public SingleClientThread(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Соединение с сервером прошло успешно !");
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws IOException{
        while(!socket.isClosed()) {
            String message = listen();
            String result = CalculationResult.calculateQuarter(Parser.parse(message));

            System.out.println(result);

            send(result);
        }
    }

    public String listen(){
        String message = null;

        try {
            message = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    public void send(String message) {
        try {
            System.out.println("Отправка");
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
