package Controller;
import MyHTTPServer.MyHTTPSettings;
import MyHTTPServer.SocketUtils;
import Sensor.RemoteInterface;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ControllerThread extends Thread{

	private final Socket requestSocket;
	private Registry registry;
	
	
	ControllerThread( Socket requestSocket){
		this.requestSocket = requestSocket;
	}

	//Controller's main logic.
	public void run() {
		try
		{
			String response;
			registry = LocateRegistry.getRegistry(MyHTTPSettings.REGISTRY_IP, MyHTTPSettings.REGISTRY_PORT);
			String query = SocketUtils.receiveMessage(requestSocket).replaceAll("\\n", "");
			System.out.println("query = " + query);
			if(query.equals("")) response = sendAvailableSensors();
			else response = processQuery(query);
			
			SocketUtils.sendMessage(requestSocket, response + "\n");
			requestSocket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Devuelve respueta
	private String processQuery(String query) {
		StringBuilder response = new StringBuilder();
		String[] queryParts = query.split("\\?");
		
		if(queryParts.length == 2) 
		{
			String resource = queryParts[0];
			String[] parameters = queryParts[1].split("&");
			
			for(String parameter: parameters) 
			{
				String[] parameterParts = parameter.split("=");
				if(parameterParts.length == 2) 
				{
						String key = parameterParts[0];
						String value = parameterParts[1];
						
						if(key.equals(MyHTTPSettings.SENSOR_KEYWORD)) 
						{
	                        response.append("<p>[").append(value).append(",").append(resource).append("] = ")
                            .append(getSensorProperty(value, resource)).append("</p>\n");
						}
				}
				
			}
			
            response.append("<a href=\"../\">Inicio</a>");
            return response.toString();
		}
		return null;
	}
	
	
	private String getSensorProperty(String sensorName, String resource) {
		try
		{
			Object returnedValue = null, remoteObject = registry.lookup(sensorName);
			if(remoteObject instanceof RemoteInterface) 
			{	
				RemoteInterface sensor = (RemoteInterface) remoteObject;
				
				if(resource.contains("="))
				{
					String[] resourceParts = resource.split("=");
					int param = 0;
					try
					{
						param = Math.max(0, Integer.parseInt(resourceParts[1]));
						
					}
					catch(Exception e) //NumberFormatException e
					{
						sensor.getClass().getMethod(resourceParts[0], int.class).invoke(sensor, param);
						returnedValue = param;
					}
				}
				else
				{
					returnedValue = sensor.getClass().getMethod(resource).invoke(sensor);
				}
				
				return returnedValue.toString();
			}
		}
		catch(Exception e)
		{
			/*
			 *  } catch (NotBoundException e) {
		            //No existe el elemento en remoto
		            return "No existe el sensor " + sensorName;
		        } catch (NoSuchMethodException e) {
		            //No existe el recurso solicitado
		            return "No existe el recurso " + (resource.contains("=") ? resource.split("=")[0] : resource) + "\n";
		        } catch (RemoteException | IllegalAccessException | InvocationTargetException e) {
		            return "El método con parámetro " + resource + " ha tenido un error y no se ha podido procesar\n";
		        }
			 * */
			
			System.err.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

    private String sendAvailableSensors() {
        try {
            StringBuilder names = new StringBuilder();
            names.append("<p>Lista de nombres de sensores disponibles:</p>\n");
            for (String remoteName : registry.list())
                if(registry.lookup(remoteName) instanceof RemoteInterface) names.append("<p>").append(remoteName).append("</p>\n");
            names.append("<p><a href=\"../\">Inicio</a></p>\n");
            return names.toString();
        } catch (RemoteException | NotBoundException e) {
            return "<h1>El controlador no se ha podido conectar con los sensores</h1>" +
                    "<p><a href=\"../\">Inicio</a></p>\n";
        }
    }	
	

	
	
}
