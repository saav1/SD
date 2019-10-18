package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


/*package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * The client program obtains a stub for the registry on the server's host, looks up the 
 * remote object's stub by name in the registry, and then invokes the 'sayHello()' method on 
 * the remote object using the stub.
 *
 *
public class Client {
	
	private Client() {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String host = (args.length < 1) ? null : args[0];
		try 
		{
			
			 * The client first obtain the stub for the registry by invoking the static 'LocateRegistry.getRegistry()' method
			 * with the hostname specified on the command line. If no hostname is specified, then null is used as the hostname
			 * indicating that the local host address should be used.
			 * 
			 * Next, the client invokes the remote mehtod 'lookup()' on the registry stub to obtain the stub for the remote object
			 * from the sever's registry.
			 * 
			 * Finally, the client invokes the 'sayHello()' method on the remote object's stub, which causes the following actions
			 * to happen:
			 *  
			 * 		1. The client-side runtime opens a connection to the server using the host and port information in the remote 
			 * 		   objects's stub and then seializes the call data.
			 * 
			 * 		2. The server-side runtime accepts the incoming call, dispatches the call to the remote objtect, and 
			 * 		   serializes the result (the reply string "Hello, world!") to the client.
			 * 
			 * 		3. The client-side runtime receives, deserializes, and returns the result to the caller.
			 * 
			 * 
			Registry registry = LocateRegistry.getRegistry(host);
			RemoteInterface stub = (RemoteInterface) registry.lookup("Hello");
			
			String response = stub.sayHello();
			System.out.print("response: " +  response);
			
		}
		catch(Exception e)
		{
			System.err.print("Client exception: " + e.toString());
			e.printStackTrace();
			
		}
	}

}*/
