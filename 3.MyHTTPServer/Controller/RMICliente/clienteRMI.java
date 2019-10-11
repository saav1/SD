import java.rmi.*;
import java.io.*;

public class clienteRMI
{

	/*Crear nueva instancia del cliente_rmi*/
	public clienteRMI(){}

	private int pedirValores(int op, int resultado, InterfazSensores objetoRemoto)
	{


		InputStreamReader ent = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(ent);

		try{
		
			if(op == 1)
			{	
				resultado = objetoRemoto.getVolumen();
			}
			else if(op == 2)
			{
				resultado = objetoRemoto.getFecha();	
			}
			else if(op == 3)
			{
				resultado = objetoRemoto.getLed();	
			}

		}
		catch(Exception exc)
		{
			System.out.println("Error al realizar la operación " + exc);	
		}
	
		return resultado;
	}

	private void pedirOperacion(String host, String port)
	{
		int op;
		int salir = 0;
		int resultado = 0;
		char respuesta = 'x';

		InterfazSensores objetoRemoto = null;
		InputStreamReader ent = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(ent);

		String servidor = "rmi://" + host + ":" + port + "/Sensor";

		try
		{
			System.setSecurityManager(new RMISecurityManager());
			objetoRemoto = (InterfazSensores) Naming.lookup(servidor);
		}
		catch(Exception ex)
		{
			System.out.println("Error al instanciar el objeto remoto " + ex);
			System.exit(0);
		}

		while(salir == 0)
		{
			op = 0;

			while(op != 1 && op != 2)
			{
				System.out.println("[1] GET VOLUMEN");
				System.out.println("[2] GET FECHA");
				System.out.println("[1] GET LED");

				try
				{
					op = new Integer(buf.readLine()).intValue();
				}
				catch(Exception e)
				{	
					op = 0; 
				}
			}

			resultado = pedirValores(op, resultado, objetoRemoto);
			respuesta = 'x';

			while(respuesta != 's' && respuesta != 'n')
			{
				System.out.println("El resultado es: " + resultado);
				System.out.println("Desea realizar otra operación? [s,n]: ");

				try
				{
					respuesta = buf.readLine().charAt(0);
				}
				catch(Exception e)
				{
					respuesta = 'x';
				}
			}

			if(respuesta != 's')
			{
				salir = 1;
			}
		}

		objetoRemoto = null;
		return;
	}


	private void menu(String host, String port)
	{
		int opc = 0;
		InputStreamReader ent = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(ent);

		while(opc != 1 && opc != 2)
		{
			System.out.println("[1] Realizar operación.");
			System.out.println("[2] Salir.");
			System.out.println("Indique la opción a realizar: ");

			try
			{
				opc = new Integer(buf.readLine()).intValue();
			}
			catch(Exception e)
			{
				opc = 0;
			}
		}

		if(opc == 1)
		{
			pedirOperacion(host, port);
		}
		else
		{
			System.exit(0);
		}

		return;
	}



	public static void main(String[] args)
	{

		String host;
		String port;

		clienteRMI cr = new clienteRMI();

		int i = 0;

		if(args.length < 2)
		{
			
			try
			{
				System.out.println("Debe indicar la dirección del servidor");
			}
			catch(Exception e)
			{
				System.out.println("Error: " + e);
			}

			return;
		}

		host = args[0];
		port = args[1];

		while(i == 0)
		{
			cr.menu(host, port);
		}
	}
}
