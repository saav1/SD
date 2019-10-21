package MyHTTPServer;

import java.io.*;
import java.net.*;

public class MyHTTPServer {

	public static void main(String args[]) {
		try
		{
			init();
		}
		catch(Exception e)
		{
			System.err.println("Server's config file not found " + e.toString());
		}
	}
	
	private static void init() {
		
		try
		{
			SocketUtils.displayIPAdresses();
			ServerSocket serverSocket = new ServerSocket(MyHTTPSettings.SERVER_PORT);
			System.out.println("Socket opened at port: " + serverSocket.getLocalPort());
		
			while(true)
			{
				Socket requestSocket = serverSocket.accept();
				System.out.println("Servig to " + requestSocket.getRemoteSocketAddress());
				
				if(Thread.activeCount() <= MyHTTPSettings.MAX_SERVER_CONNECTIONS) 
				{
					Thread t = new MyHTTPThread(requestSocket);
					t.start();
				}
				else
				{
					System.err.println("Connection with : " + requestSocket.getRemoteSocketAddress() + "closed due to connection limit");
					requestSocket.close();
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("Unable to open socket..");
		}
		
	}
	
	
}
