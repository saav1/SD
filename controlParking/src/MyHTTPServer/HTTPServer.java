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
	
	static int port;
	static int MAX_CONNECTIONS;
	
	private Socket connect;
	
	public HTTPServer(Socket connect) {
		
		this.connect = connect;
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
	
	
	public static void main(String[] args) { 															//Args: HTTPServer.class <PORT> <MAX_CONNECTIONS>
		
		/*
		System.out.println("Args length: " + args.length);
		System.out.println("args[0]: " + args[0]);
		System.out.println("args[1]: " + args[1]);*/

		
		if(args.length <= 2) 
		{
			try 
			{
				//ServerSocket socketServidor = new ServerSocket(Integer.parseInt(args[0])); 				//Parameter PORT to create server socket
				HTTPServer.port = 1041;
				HTTPServer.MAX_CONNECTIONS = 10;
				ServerSocket socketServidor = new ServerSocket(HTTPServer.port); 									//Parameter PORT to create server socket
				
				//System.out.println("Server started. \nListening for connections on port: " + args[0]);
				System.out.println("Server started. \nListening for connections on port: " + HTTPServer.port);

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
			
			if(input[0].equals("GET"))
			{
				if(input[input.length - 2].equals("/"))
				{
					input[input.length - 2] += DEFAULT_FILE;
				}
				
				File file = new File(new File("."), input[input.length - 2]);
				
				int fileLength = (int)file.length();
				
				//   text/html
				
				byte[] fileData = readFileData(file, fileLength);
			
				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 200 OK");
				out.println("Server: HTTPServer by STALYN ALEJNDRO : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + fileLength);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				
				
				bw.write(fileData, 0, fileLength);
				bw.flush();
				
			}
			
			
			
		}
		catch(FileNotFoundException f)
		{
			System.err.println("ERROR FILE NOT FOUND: " + f.getMessage());
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

}










































