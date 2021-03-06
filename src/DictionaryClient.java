
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DictionaryClient {
	public static void main(String[] args) {
		
		try {
			// Connect to Server
			Socket connection = new Socket("localhost", 8888);
			
			InputStream inputStream = connection.getInputStream();
			Scanner streamReader = new Scanner(inputStream);
			
			OutputStream outputStream = connection.getOutputStream();
			PrintWriter streamWriter = new PrintWriter(outputStream, true);
			
			InputStream keyboardInputStream = System.in;
			Scanner keyboardReader = new Scanner(keyboardInputStream);
			
			
			while(true){
				// Read request from user keyboard
				System.out.print("Input your word: ");
				String request = keyboardReader.nextLine();
				
				// Send request to server
				streamWriter.println(request);
				
				// Read response from server
				String response = streamReader.nextLine();
				
				// Check response
				if(response.equals("//close")){
					System.out.println("Good bye!");
					break;
					
				} else {
					System.out.println(response);
				}
			}
			
			// Close resources
			streamReader.close();
			keyboardReader.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
