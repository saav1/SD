package MyHTTPServer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;


public class HTTPServer implements Runnable{

	static final String DEFAULT_FILE = "src/index.html";
	
	static int PORT;
	static int MAX_CONNECTIONS;
	
	private Socket connect;
	
	public HTTPServer(Socket connect) {
		
		this.connect = connect;
	}
	
	
	public static void main(String[] args) { 															//Args: HTTPServer.class <PORT> <MAX_CONNECTIONS>
		
		/*
		System.out.println("Args length: " + args.length);
		System.out.println("args[0]: " + args[0]);
		System.out.println("args[1]: " + args[1]);*/

		
		if(args.length <= 2) 
		{
			try 
			{
				//HTTPServer.PORT = Integer.parseInt(args[0]);
				//HTTPServer.MAX_CONNECTIONS = Integer.parseInt(args[1]);
				
				HTTPServer.PORT = 1999;
				HTTPServer.MAX_CONNECTIONS = 10;
				
				ServerSocket socketServidor = new ServerSocket(HTTPServer.PORT); 				//Parameter PORT to create server socket
				System.out.println("Server started. \nListening for connections on port: " + HTTPServer.PORT);

				while(Thread.activeCount() <= HTTPServer.MAX_CONNECTIONS) 
				{
					Socket clientSocket = socketServidor.accept();
					HTTPServer myServer = new HTTPServer(clientSocket);
					
					Thread thread = new Thread(myServer);
					thread.start();
				}
					
			}
			catch(IOException e)
			{
				System.err.println("SERVER CONNECTIN ERROR: " + e.getMessage());
			}
			
		}
		else
		{
			System.out.println("ERROR ARGS: /.class <PORT> <MAX_CONNECTIONS>");
		}
		
	}
	
	
	

	@Override
	public void run() { 																				//We manage our particular Client Connection
		
		System.out.println("RUN..");
		
		BufferedReader br = null;			//BufferReader
		DataOutputStream bw = null;			//BufferWriter
		PrintWriter out = null;
		String fileRequested = "";
		
		try
		{
			
			br = new BufferedReader(new InputStreamReader(connect.getInputStream()));				//We read characters from CLIENT via INPUT STREAM on the socket.
			bw = new DataOutputStream(connect.getOutputStream());
			out = new PrintWriter(connect.getOutputStream());
			
			String[] input = new String(br.readLine()).split(" ");
			
			System.out.println("input length : " + input.length);

			System.out.println(input[0].toString());
			System.out.println(input[1].toString());
			System.out.println(input[2].toString());

			
			
			if(input[0].equals("GET"))
			{
				if(input[1].equals("/")) input[1] += DEFAULT_FILE;
				File file = new File(new File("."), input[1]);
				
				
				
				int fileLength = (int)file.length();	
				
				
				byte[] fileData = readFileData(file, fileLength);
				

				
				sendMessageHTTPToClient(fileLength, 200, fileData); //OK

			}
			else
			{
				sendMessageHTTPToClient(0, 405, null); //OK

			}
			
			
			
		}
		catch(FileNotFoundException f)
		{
			sendMessageHTTPToClient(0, 404, null); //FILE NOT FOUND
			
			System.err.println("ERROR FILE NOT FOUND: " + f.getMessage());
			f.printStackTrace();
			
		}
		catch(IOException e)
		{
			System.err.println("ERROR IO EXCEPTION: " + e.getMessage());
		}
		finally
		{
			try 
			{
				br.close();
				bw.close();		
			}
			catch(Exception e)
			{
				System.err.println("ERROR CLOSSING STREAM : " + e.getMessage());
			}
		}
	}
	
	private void sendMessageHTTPToClient(int fileLength, int cod, byte[] fileData) {
		
		PrintWriter out = null;
		DataOutputStream bw = null;		
		
		try
		{
			bw = new DataOutputStream(connect.getOutputStream());
			out = new PrintWriter(connect.getOutputStream());
			
			
			if(cod == 200)//OK
			{
				out.println("HTTP/1.1 200 OK");														//We send HTTP Headers with data to client
				out.println("Server: HTTPServer by STALYN ALEJNDRO : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + fileLength);
		
			}
			else if(cod == 404)//FILE NOT FOUND
			{
				out.println("HTTP/1.1 404 FILE_NOT_FOUND");														//We send HTTP Headers with data to client
				out.println("Server: HTTPServer by STALYN ALEJNDRO : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + fileLength);
			}
			else if(cod == 405)//METHOD NOT ALLOWED
			{
				out.println("HTTP/1.1 405 METHOD_NOT_ALLOWED");														//We send HTTP Headers with data to client
				out.println("Server: HTTPServer by STALYN ALEJNDRO : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + fileLength);
			}
			else if(cod == 409)//CONTROLLER CONEXIÓN LOST
			{
				out.println("HTTP/1.1 409 CONTROLLER_CONEXION_LOST");														//We send HTTP Headers with data to client
				out.println("Server: HTTPServer by STALYN ALEJNDRO : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + fileLength);
			}
			
			out.println(); 																		// Blank line between headers and content, very important !
			out.flush(); 																		// Flush character output stream buffer
			bw.write(fileData, 0, fileLength);		
			bw.flush();
			
		}
		catch(IOException e)
		{
			System.err.println("ERROR SEND MESSAGE: " + e.getMessage());
		}
		finally
		{
			try {
				bw.close();
				out.close();
			} catch (IOException e) {
				System.err.println("ERROR .CLOSE(): " + e.getMessage());
			}
			
		}
	}
	
	
	private byte[] readFileData(File file, int fileLength) throws IOException{
		
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try
		{
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		}
		finally
		{
			if(fileIn != null)
			{
				fileIn.close();
			}
		}
		
		return fileData;
		
	}
	

	


}










































