package MyHTTPServer;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static MyHTTPServer.SocketUtils.receiveMessage;
import static MyHTTPServer.SocketUtils.sendMessage;

public class MyHTTPThread extends Thread{

	private final Socket requestSocket;
	
	
	MyHTTPThread(Socket requestSocket){
		
		this.requestSocket = requestSocket;
	}
	
	
	@Override
	public void run() {
		try
		{
			String request = receiveMessage(requestSocket);
			String[] requestParts = request.split(" ");
			
			if(requestParts[0].equals("GET"))
			{
				String url = requestParts[1];
				
				if(url.equals("/")) 
				{
					url += (MyHTTPSettings.DEFAULT_INDEX_PAGE);
					
				}
				
				String[] routeParts = url.split("/");
				
				if(routeParts[1].equals(MyHTTPSettings.DYNAMIC_CONTENT_KEYWORD)) {
					serveDynamicRequest(routeParts.length == 3 ? routeParts[2] : "");
					
				}
				else
				{
					serveStaticRequest(routeParts[1]);
				}
				

			}
			else
			{
				System.out.println("ERROR 405 for client " + requestSocket.getRemoteSocketAddress() + "Requested method: " + requestParts[0]);
				sendHTTPResponse(MyHTTPStatusCode.METHOD_NOT_ALLOWED.getHTMLError(), MyHTTPStatusCode.METHOD_NOT_ALLOWED);
			}
			
			requestSocket.close();
			
		}
		catch(IOException e)
		{
			System.out.println("ERROR" + e.toString());
		}
	}
	
	private void serveDynamicRequest(String query) throws IOException{
		try
		{
			Socket s = new Socket(MyHTTPSettings.CONTROLLER_IP, MyHTTPSettings.CONTROLLER_PORT);
			sendMessage(s, query + "\n\r");
			String response = receiveMessage(s);
			s.close();
			
			if(!response.replaceAll("\\n", "").equals("null")) 
			{
				sendHTTPResponse(response, MyHTTPStatusCode.OK);
			}
			else
			{
				throw new IOException();
			}
		}
		catch(IOException e)
		{
			sendHTTPResponse(MyHTTPStatusCode.CONFLICT.getHTMLError(), MyHTTPStatusCode.CONFLICT);
		}
	}
	
	private void serveStaticRequest(String path) throws IOException {
		try
		{
			sendHTTPResponse(HTMLEntityEncode(new String(Files.readAllBytes(Paths.get(path)))) + "\n", MyHTTPStatusCode.OK  );
		}
		catch(IOException e)
		{
			try
			{
				sendHTTPResponse(MyHTTPStatusCode.NOT_FOUND.getHTMLError(), MyHTTPStatusCode.NOT_FOUND);
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
			System.err.println("Client " + requestSocket.getRemoteSocketAddress() + "got ERROR 404 for file " + path);
		}
	}
	
	//Sends a generic HTTP RESPONSE
	private void sendHTTPResponse(String fileContent, MyHTTPStatusCode statusCode) throws IOException{
		String response = "HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getDescription() + "\n" +
				"Connection: close\n" + 
				"Content Length: " + fileContent.length() + "\n" +
				"Content Type: text/html\n" +
				"Server: practicas-sd \n\n" +
				fileContent;
		
		sendMessage(requestSocket, response);
	}
	
	//Encodes a String to its HTML special characters
	static String HTMLEntityEncode(String s) {
		StringBuilder builder = new StringBuilder();
		for(char c: s.toCharArray()) builder.append((int) c < 128 ? c : "&#" + (int)c + ";");
		return builder.toString();
	}
	
}













