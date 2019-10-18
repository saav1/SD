
package example.hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            //e.printStackTrace();
        }
    }
}

/*package example.hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

 * A server class, int this context, is the class which has a main method that creates 
 * an instance of the remote object implementation, exports the remote object, and then 
 * binds that instance to a name in a JavaRMI regsitry. 
 * 
 * The class that contains this main method could be the implementation class itself, or another class entirely.
 * 
 * The main method for the server is defined in the class Server which also implements the remote interface 'RemoteInterface'
 * The server's main does the following:
 * 		1. Create and export a remote object.
 * 		2. Register the remote object with javaRMI registry.
 * 
 * The implementation class Server implements the remote interface RemoteInterface, provinding an implementation
 * for the remote method 'sayHello()'. 
 * The method 'sayHello()' does not need to declare that it throws any exception because the method implementation 
 * itself does not throw RemoteException nor does it throw any other checked exceptions.
 * 
 * Note: A class can define methods not specified in the remote interface, but those methods can only be invoked within 
 * machine running and cannot be invoked remotely. 
 * 

public class Server implements RemoteInterface{
	
	public Server() {}
	
	public String sayHello() {
		return "Hello, world!";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			
			 * The server needs to create the remote object that provides the service.
			 * Additionally, the remote object must be exported to the JavaRMI runtime so 
			 * that it may receive incoming remote calls.
			 * 
			 * The static method 'UnicastRemoteObject.exportObject()' exports the suplied remote object
			 * incoming remote method invocations on an anonymous TCP port and returns the stub for the 
			 * remote object to pass to clients. As a result of object's class and contains the host name
			 * and port over which the remote object can be contacted.
			 * 
			Server obj = new Server();
			RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(obj, 0);
			
			
			 * REGISTER THE REMOTE OBJECT WITH A Java RMI registry.
			 * 
			 * For a caller(client, peer, o applet) to be able to invoke a method on a remote object, that caller must
			 * obtain a stub for the remote object. For bootstrapping, Java RMI provides a registry API for applications
			 * to bind a name to a remote object's stub and for clients to look up remote objects by name in order to obtain
			 * their stubs.
			 * 
			 * A JavaRMI registry is a simplied name service that allows clients to get a reference (a stub) to a remote object.
			 * In general, a registry is used only to locate the first remote object a client needs to use. 
			 * Then, typically, that first object would in turn provide application-specific suport for finding other objects.
			 * For example, the reference can be obtained as a parameter to, or return value from, another remote method call.
			 * For a discussion on how it works, please take a look at 'Applying the Factory Pattern to JavaRMI'
			 * 
			 * Once a remote object is registered on the server, callers can look up the object by name, obtain a remote object
			 * reference, and then invoke remote methods on the object.
			 * 
			 * 
			 * The static method 'LocateRegistry.getRegistry()' that takes no arguments return a stub that implements the remote
			 * interface java.rmi.registry.Registry and sends invocations to the registry on server's local host on the default
			 * registry port of 1099. The bind method is then invoked on the registry stub in order to bind the remote object's
			 * to the name "Hello" in the registry.
			 * 
			 * Note: The call to LocalteRegistry.getRegistry() simply returns an appropriate stub for a registry. The call does not
			 * check to see if a registry is actually running. If no registry is running on TCP port 1099 of the local host when the 
			 * bind method is invoked, the server will fail with a RemoteException.
			 * 
			 * 
			
			
			//Bind the remote object's stub in the registry.
			//The following code obtains a stub for a registry on the local host and default registry port and then 
			//uses the registry stub to bind the name "Hello" to the remote object's stub in that registry.
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);
			
			System.err.print("Server ready..");
		}
		catch(Exception e)
		{
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			
		}
	}

}*/
