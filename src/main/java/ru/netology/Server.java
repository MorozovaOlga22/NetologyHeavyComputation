package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String SERVER_HOST = "127.0.0.1";
    public static final int SERVER_PORT = 30_666;

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        try (final Socket socket = serverSocket.accept();
             final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            final String inputStr = reader.readLine();
            try {
                final int number = Integer.parseInt(inputStr);
                final long fibonacciNumber = calculateFibonacciNumber(number);
                writer.println("Число Фибоначчи: " + fibonacciNumber);
            } catch (Exception e) {
                writer.println("Не удалось рассчитать результат. Проверьте формат ввода");
            }
        }
    }

    private static long calculateFibonacciNumber(int number) {
        if (number >= -1 && number <= 1) {
            return number;
        }

        long prevNum = 0;
        long sum = 1;
        final int numAbs = Math.abs(number);

        for (int i = 0; i < numAbs - 1; i++) {
            sum += prevNum;
            prevNum = sum - prevNum;
        }

        final int signFactor = number > 0 ? 1 : (int) Math.pow(-1, numAbs + 1);
        return signFactor * sum;
    }
}