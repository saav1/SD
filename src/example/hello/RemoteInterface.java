package example.hello;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * A remote object is an instance of a class that implements a remote interface. 
 * A remote interface extends the interface java.rmi.Remote and declares a set of remote methods.
 * Each remote method must declare java.rmi.RemoteException in its throws clause.
 *
 * */

public interface RemoteInterface extends Remote {
	//Remote mehtods will report failures by throwing a java.rmi.RemoteException;
	String sayHello() throws RemoteException;
	
}
