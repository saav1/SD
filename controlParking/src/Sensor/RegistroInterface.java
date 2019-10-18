package Sensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface RegistroInterface extends RemoteInterface{
	void registrarSensor(RemoteInterface sensor) throws RemoteException;
	void desregistrandoSensor(RemoteInterface sensor) throws RemoteException;
}
