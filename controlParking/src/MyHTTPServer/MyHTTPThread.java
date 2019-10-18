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
	private final MyHTTPSettings settings;
	
	
	MyHTTPThread(MyHTTPSettings settings, Socket requestSocket){
		
		this.requestSocket = requestSocket;
		this.settings = settings;
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
					url += (settings.DEFAULT_INDEX_PAGE);
					
				}
				
				String[] routeParts = url.split("/");
				
				if(routeParts[1].equals(settings.DYNAMIC_CONTENT_KEYWORD)) {
					serveDynamicRequest(routeParts.length == 3 ? routeParts[2] : "");
					
				}
				else
				{
					System.out.println("ERROR 405 for client " + requestSocket.getRemoteSocketAddress() + "Requested method: " + requestParts[0]);
					sendHTTPResponse(MyHTTPStatusCode.METHOD_NOT_ALLOWED.getHTMLError(), MyHTTPStatusCode.METHOD_NOT_ALLOWED);
				}
				
			}
			
		}
		catch(IOException e)
		{
			System.out.println("ERROR" + e.toString());
		}
	}
	
	
}
