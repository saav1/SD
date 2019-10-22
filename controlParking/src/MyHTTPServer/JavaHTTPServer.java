package MyHTTPServer;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.StringTokenizer;


//Each Client Connection will be managed in a dedicated Thread.
public class JavaHTTPServer implements Runnable{
	
	static final File WEB_ROOT = new File(".");
	static final String DEFAULT_FILE = "index.html";
	static final String FILE_NOT_FOUND = "404.html";
	static final String METHOD_NOT_SUPPORTED = "not_supported.html";
	
	//Port to listen connection
	static final int PORT = 8080;
	
	//Verbose Mode
	static final boolean verbose = true;
	
	//Client Connection via Socket Class
	private Socket connect;
	
	//Constructor
	public JavaHTTPServer(Socket c) {
		connect = c;
	}
	
	//Main
	public static void main(String[] args) {
		try
		{
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server stated.\nListening for connections on port: " + PORT + "...\n");
			
			//We listen until user halts server execution.
			while(true) 
			{
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());
				if(verbose)
				{
					System.out.println("Connection opened. ( " + new Date() + " )");
				}
				//Create dedicated thread to manage the client connection.
				Thread thread = new Thread(myServer);
				thread.start();
			}
		}
		catch(IOException e)
		{
			System.err.println("Server Connection error: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		//We manage our particular client connection.
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedOutputStream dataOut = null;
		String fileRequested = null;
		
		try
		{
			//We read characters from the client via input stream on the socket.
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			
			//We get character output stream to client. (for headers).
			out = new PrintWriter(connect.getOutputStream());
			
			//Get binary output stream to client. (For requested data).
			dataOut = new BufferedOutputStream(connect.getOutputStream());
			
			//Get first line of the request from the client.
			String input = in.readLine();
			
			//We parse the request with a string tokenizer
			StringTokenizer parse = new StringTokenizer(input);
			
			//We get the HTTP mehtod of the client.
			String method = parse.nextToken().toUpperCase();
			
			//We get file requested.
			fileRequested = parse.nextToken().toLowerCase();
			
			//WE suport only GET and HEAD methods, we check
			if(!method.equals("GET") && !method.equals("HEAD"))
			{
				if(verbose)
				{
					System.out.println("501 NOT IMPLEMENTED: " + method + " MEHTOD.");
				}
				
				//We return the not supported file to the client.
				File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				String contentMimeType = "text/html";
				
				//Read content to return to client.
				byte[] fileData = readFileData(file, fileLength);
				
				//We send HTTP Headers with data to client.
				out.println("HTTP/1.1 501 NOT IMPLEMENTED ");
				out.println("Server: Java HTTP Server from SSaurel : 1.0 ");
				out.println("Date: " + new Date());
				out.println("Content-type: " + contentMimeType);
				out.println("Content-lenght: " + fileLength);
				out.println();  //blank line between headers and content, very important!
				out.flush();	//flush character output stream buffer
				
				//file
				dataOut.write(fileData, 0, fileLength);
				dataOut.flush();
				
			}
			else
			{
				//GET or HEAD method
				if(fileRequested.endsWith("/n"))
				{
					fileRequested += DEFAULT_FILE;
				}
				
				File file = new File(WEB_ROOT, fileRequested);
				int fileLength = (int) file.length();
				String content = getContentType(fileRequested);
				
				//GET method so we return content
				if(method.equals("GET"))
				{
					byte[] fileData = readFileData(file, fileLength);
					
					//send HTTP HEADERS
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Java HTTP Server from SSaurel : 1.0" );
					out.println("Date : " + new Date());
					out.println("Content-type: " + content);
					out.println("Content-length: " + fileLength);
					out.println();
					out.flush();
					
					dataOut.write(fileData, 0, fileLength);
					dataOut.flush();
					
				}
				
				if(verbose)
				{
					System.out.println("File " + fileRequested + " of type " + content + "returned.");
				}
				
			}
			
			

		}
		catch(FileNotFoundException e)
		{
			try
			{
				fileNotFound(out, dataOut, fileRequested);
			}
			catch(IOException ie)
			{
				System.err.println("Error with file not found exception : " + ie.getMessage());
			}
		}
		catch(IOException ieo)
		{
			System.err.println("Server error: " + ieo.getMessage());
		}
		finally
		{
			try
			{
				in.close();
				out.close();
				dataOut.close();
				connect.close(); //we close socket connection
			}
			catch(Exception e)
			{
				System.err.println("Error closing stream: " + e.getMessage());
			}
			
			if(verbose)
			{
				System.err.println("Connection closed. \n");
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
	
	
	//return supported MIME types
	private String getContentType(String fileRequested) {
		if(fileRequested.endsWith(".htm")) 
		{
			return "text/html";
		}
		else
		{
			return "text/plain";
		}
	}
	
	private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(WEB_ROOT, FILE_NOT_FOUND);
		int fileLength = (int)file.length();
		String content = "text/html";
		byte[] fileData = readFileData(file, fileLength);
		
		out.println("HTTP/1.1 404 File Not Found");
		out.println("Server: Java HTTP Server from Stalyn : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); //BLANK LINE BETWEEN HEADERS AND CONTENT, VERY IMPORANT!
		out.flush();
		
		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
		
		if(verbose)
		{
			System.out.println("File " + fileRequested + " not found!");
		}
	}
	
}













