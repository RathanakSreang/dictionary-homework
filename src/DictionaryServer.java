
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.Translation;

public class DictionaryServer {
	public static void main(String[] args) {
		try {
			System.out.println("Bind port ...");
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8888);
			while(true) {
				// Wait and accept connection from client
				System.out.println("Wait for client ...");
				Socket connection = serverSocket.accept();
				Translation translationThread = new Translation(connection);
				translationThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
