package sondas;
import java.rmi.*;
import java.rmi.server.*;
import java.time.*;
import java.io.*;


public class Sonda extends UnicastRemoteObject implements sondaInterface{

	private String nombre;
	public int volumen, led;
	public String ultimaFecha;
	
	
	//Constructor.
	Sonda(String sensor)throws RemoteException{
		try 
		{
			//Leo el fichero y guardo lo datos.
			leerSensor(sensor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.print("Error : " + e.toString());
		}
	}
	
	
	@Override
	public int getVolumen() throws RemoteException {
		// TODO Auto-generated method stub
		
		return volumen;
	}

	@Override
	public String getFecha() throws RemoteException {
		// TODO Auto-generated method stub
		
		return ultimaFecha;
	}

	@Override
	public int getLed() throws RemoteException {
		// TODO Auto-generated method stub
		
		return led;
	}
	
	@Override
	public void setLed(int led) throws RemoteException {
		// TODO Auto-generated method stub
		this.led = led;
		
		//Hay que guardar los datos en el fichero, cuando se modifican.
		guardarSensor(nombre);
	}

	
	private void leerSensor(String sensor) throws Exception{
		String sr;
		BufferedReader br = new BufferedReader(new FileReader(sensor));
		while( (sr = br.readLine()) != null )
		{
			System.out.print(sr);
			String[] item = sr.split("=");
			if(item[0] == "Volumen") this.volumen = Integer.parseInt(item[1]);
			if(item[0] == "UltimaFecha") this.ultimaFecha = item[1];
			if(item[0] == "Led") this.led = Integer.parseInt(item[1]);
		}
	}
	
	private void guardarSensor(String nombre) {
		FileWriter fw = null;
		String sr;
		try 
		{
			fw = new FileWriter(new File(nombre));
			sr = "Volumen=" + volumen + "\nUltimaFecha=" + ultimaFecha + "\nLed" + led;
			fw.write(sr);
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.toString());
		}
		
	}
	
	
	
	@Override
	public String toString() {
		return "Sensor [nombre=" + nombre + ", volumen=" + volumen + ", led=" + led + ", ultimaFecha=" + ultimaFecha
				+ "]";
	}


	public static void main(String[] args) throws Exception {
	
	}

}
