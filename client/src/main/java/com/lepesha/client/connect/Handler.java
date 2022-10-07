package com.lepesha.client.connect;

import com.lepesha.client.parse.Parser;
import javafx.scene.Parent;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Handler {
    private Socket socket;
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public Handler(String hostAddress, int port) throws ConnectException {
        try {
            socket = new Socket(hostAddress, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new ConnectException(e.getMessage());
        }
    }

    public double[] produce() {
        String message = listen();
        double[] parseResult = Parser.parse(message);
        for (double num:
                parseResult) {
            System.out.println(num);
        }
        return parseResult;
    }

    private String listen(){
        String message = null;
        try {
            message = reader.readLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void send(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareToSend(String xField, String yField) {
        send(xField +  ";" + yField);
    }
}
