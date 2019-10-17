package Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Sensor.RemoteInterface;

public interface RegisterInterface extends Remote{
	
	public void sensorReg(RemoteInterface sensor) throws RemoteException;
	public void sensorBaja(RemoteInterface sensor) throws RemoteException;

}
