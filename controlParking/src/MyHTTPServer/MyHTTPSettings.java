package MyHTTPServer;



public class MyHTTPSettings {
	
	public static int MAX_SERVER_CONNECTIONS = 2;
	public static int SERVER_PORT = 1089;
	public static int CONTROLLER_PORT = 1089;
	public static int REGISTRY_PORT = 1027;
	public static String CONTROLLER_IP =  "172.20.43.76";
	public static String REGISTRY_IP = "172.20.43.76";
	public static String DYNAMIC_CONTENT_KEYWORD = "controladorSD";
	public static String DEFAULT_INDEX_PAGE = "index.html";
	public static String SENSOR_KEYWORD = "sensor";
	


	public String toString() {
		return "MyHTTPSettings [MAX_SERVER_CONNECTIONS=" + MAX_SERVER_CONNECTIONS + ", SERVER_PORT=" + SERVER_PORT
				+ ", CONTROLLER_PORT=" + CONTROLLER_PORT + ", REGISTRY_PORT=" + REGISTRY_PORT + ", CONTROLLER_IP="
				+ CONTROLLER_IP + ", REGISTRY_IP=" + REGISTRY_IP + ", DYNAMIC_CONTENT_KEYWORD="
				+ DYNAMIC_CONTENT_KEYWORD + ", DEFAULT_INDEX_PAGE=" + DEFAULT_INDEX_PAGE + ", SENSOR_KEYWORD="
				+ SENSOR_KEYWORD + "]";
	}
	
	
}
