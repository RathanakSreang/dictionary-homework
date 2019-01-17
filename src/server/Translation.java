package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Translation extends Thread {
	Socket connection;
	
	public Translation(Socket connection) {
		this.connection = connection;
	}
	
	@Override
	public void run() {
		InputStream inputStream;
		try {
			inputStream = connection.getInputStream();
			Scanner streamReader = new Scanner(inputStream);

			OutputStream outputStream = connection.getOutputStream();
			PrintWriter streamWriter = new PrintWriter(outputStream, true);

			Dictionary dictionary = new Dictionary();
			while(true){
				String request = streamReader.nextLine();
				if(request.equals("//close")){
					// Send close response back to client and close connection 
					streamWriter.println(request);
					break;
				}
				else if (request.equals("//status")) {
					streamWriter.println(dictionary.getLang());
				}
				else if (request.equals("//switch")) {
					dictionary.switchLang();
					streamWriter.println("swtiched: " + dictionary.getLang());
				}
				else {
					streamWriter.println(dictionary.search(request));
				}
			}
			// Close resources
			System.out.println("Disconnect from client ...");
			streamReader.close();
			connection.close();
			//serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
