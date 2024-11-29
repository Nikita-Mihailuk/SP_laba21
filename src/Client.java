import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Введите начальное значение: ");
            int startValue = scanner.nextInt();
            System.out.print("Введите количество итераций: ");
            int iterationCount = scanner.nextInt();

            writer.println(startValue);
            writer.println(iterationCount);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("получено от сервера: " + inputLine);

                writer.println(inputLine);
                System.out.println("отправлено серверу: " + inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
