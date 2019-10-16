package sondas;
import java.rmi.*;
import java.rmi.RemoteException;

public interface sondaInterface extends Remote{
		
	public int getVolumen() throws RemoteException;
	public String getFecha() throws RemoteException;
	public int getLed() throws RemoteException;
	public void setLed(int led) throws RemoteException;

}
