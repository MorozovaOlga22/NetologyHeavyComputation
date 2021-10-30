package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static ru.netology.Server.SERVER_HOST;
import static ru.netology.Server.SERVER_PORT;

public class Client {
    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(SERVER_HOST, SERVER_PORT);

        try (final Scanner scanner = new Scanner(System.in);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Введите целое число");
            final String number = scanner.nextLine();
            writer.println(number);
            System.out.println(reader.readLine());
        }
    }
}