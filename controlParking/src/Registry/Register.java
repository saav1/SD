package Registry;

/*
import Parking.MyHTTPServer.MyHTTPSettings;
import Parking.Sensor.SensorServic
*/

import Sensor.RemoteInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Register implements RegisterInterface{

	private final Registry registry;
	
	Register(Registry registry) throws RemoteException{
		super();
		this.registry = registry;
		
	}
	
	


	@Override
	public void sensorReg(RemoteInterface sensor) throws RemoteException {
		
		try
		{
			registry.rebind(sensor.getNombre(), sensor);
			System.out.println("Registered: " + sensor.getNombre());
		}
		catch(Exception ex)
		{
			System.err.println("Error al registra objeto: " + ex.toString());
			ex.printStackTrace();
			
		}
	}

	@Override
	public void sensorBaja(RemoteInterface sensor) throws RemoteException {
		try
		{
			registry.unbind(sensor.getNombre());
			System.out.println("Dando de baja: " + sensor.getNombre());
		}
		catch(Exception ex)
		{
			System.err.println("Error al dar de baja : " +  ex.toString());
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

	}
}
