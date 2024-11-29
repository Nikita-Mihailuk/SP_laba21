import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        System.out.println("Сервер запущен...");
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String startValueLine = reader.readLine();
            String iterationCountLine = reader.readLine();

            int response = Integer.parseInt(startValueLine);
            int maxIterations = Integer.parseInt(iterationCountLine);

            System.out.println("Начальное значение: " + response);
            System.out.println("Количество итераций: " + maxIterations);

            writer.println(response);
            System.out.println("отправлено клиенту: " + response);

            for (int i = 0; i < maxIterations; i++) {
                String inputLine = reader.readLine();
                if (inputLine == null) break;

                int request = Integer.parseInt(inputLine);
                System.out.println("прислал клиент: " + request);

                response = request + 1;
                writer.println(response);
                System.out.println("отправлено клиенту: " + response);
            }

            System.out.println("Итерации завершены. Клиент отключился");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
