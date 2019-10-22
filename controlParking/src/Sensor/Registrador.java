package Sensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Registrador extends UnicastRemoteObject{

	public static final String NOMBRE = "Registro";
	private final Registry registro;
	
	
	
	Registrador(Registry registro)throws RemoteException{
		super();
		this.registro = registro;
	}
	
	public void registrarSensor(RemoteInterface sensor) throws RemoteException{
		try
		{
			registro.rebind(sensor.getNombre(), sensor);
			System.out.println("Registrado Sensor: " +  sensor.getNombre());
			
		}
		catch(RemoteException e)
		{
			System.out.println("ERROR REGISTRANDO SENSOR: " + e.getMessage());
			throw e;
		}
	}
	
	
	public void desregistrardoSensor(RemoteInterface sensor) throws RemoteException{
		try
		{
			registro.unbind(sensor.getNombre());
			System.out.println("Desregistrando Sensor: " + sensor.getNombre());
		}
		catch(NotBoundException e)
		{
			System.out.println("ERROR AL DESREGISTRAR  SENSOR: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		if(args.length >= 2) {
			
			Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			Registrador master = new Registrador(registry);
            registry.rebind(Registrador.NOMBRE, master);	
            
            System.out.println("REGISTRO NOMBRE: " + Registrador.NOMBRE);
			System.out.println("REGISTRADOR OK -> " + args[0] + " : " + args[1]);
			
		}else {
			
			System.out.println("Wrong args: <IP> <PORT>");
		}
		
	}


}






























