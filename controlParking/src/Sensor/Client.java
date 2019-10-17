package Sensor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	Client(){}
	
	public void requestServices( String host, String sensor ) {
		
		try 
		{
			Registry registry = LocateRegistry.getRegistry(host);	
			//RemoteInterface stub = (RemoteInterface)registry.lookup(sensor);
			
			clientMenu(sensor, registry);
		}
		catch(Exception e)
		{
			System.err.println("Error al pedir servicios: " + e.toString());
			e.printStackTrace();
			
		}
		
	}
	
	private void clientMenu(String sensor, Registry registry) {
		
		
		String op = "n";
		System.out.println("............MENU............");
		System.out.println("Elige el sensor");
		System.out.println("[1]. Get Volumen");
		System.out.println("[2]. Get Fecha");
		System.out.println("[3]. Get Led");
		
		
		
		try
		{
			RemoteInterface stub = (RemoteInterface)registry.lookup(sensor);	
			String response = String.valueOf(stub.getVolumen());
		}
		catch(Exception e) {
			System.out.println("Error al recibir respuesta : " + e.toString());
			e.printStackTrace();
		}
		


		

	}


}
