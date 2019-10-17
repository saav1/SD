package Sensor;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.io.*;


public class Sensor implements RemoteInterface{
	
	private String nombre;
	public String ultimaFecha;
	public int volumen, led;
	
	
	Sensor(String file)
	{
		try
		{
			this.nombre = file.substring(0, file.lastIndexOf('.'));
			readSensor(file);
			registrySensor();
		}
		catch(Exception e)
		{
			System.err.println("Error leer " + this.nombre + " : " + e.toString());
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public int getVolumen() throws RemoteException { return volumen; }

	@Override
	public String getFecha() throws RemoteException { return ultimaFecha; }

	@Override
	public int getLed() throws RemoteException { return led; }
	
	@Override
	public void setLed(int led) throws RemoteException {
		this.led = led;
		saveSensor(nombre);
	}

	
	private int readSensor(String file) throws Exception{
		String sr;
		BufferedReader br = new BufferedReader(new FileReader(file));

		while( (sr = br.readLine()) != null )
		{
			String[] item = sr.split("=");
			if(item[0].compareTo("Volumen") == 0 )this.volumen = Integer.parseInt(item[1]);
			if(item[0].compareTo("UltimaFecha") == 0 ) this.ultimaFecha = item[1];
			if(item[0].compareTo("Led") == 0 ) this.led = Integer.parseInt(item[1]);
		}
		
		return 0; //OK
	}


	private int saveSensor(String file) 
	{
		FileWriter fw = null;
		String sr;
		try 
		{
			fw = new FileWriter(new File(nombre));
			sr = "Volumen=" + volumen + "\nUltimaFecha=" + ultimaFecha + "\nLed" + led;
			fw.write(sr);
			fw.close();
		}
		catch(Exception e)
		{
			System.err.println("Error guardar " + this.nombre + " : " + e.toString());
			e.printStackTrace();
			return -1; //NOT OK.
		}	
		
		return 0; //OK.
	}
	
	private int registrySensor() 
	{
		try
		{
			RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(this, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(this.nombre, stub);
			
			System.err.println(this.nombre + " listo...");
		}
		catch(Exception e) 
		{
			System.err.println("Error registrar " + this.nombre + " : " + e.toString());
		}
		
		return 0;//OK
	}
	

	@Override
	public String toString() {
		return "Sensor [nombre = " + this.nombre + ", volumen = " + this.volumen + ", led = " + this.led + ", ultimaFecha = " + this.ultimaFecha
				+ "]";
	}
	
	public static void main(String[] args) {
		Sensor s = new Sensor(args[0]);
		System.out.println(s.toString());
	}
	
}
