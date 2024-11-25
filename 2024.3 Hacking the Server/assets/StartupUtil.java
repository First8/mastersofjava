import java.lang.reflect.*;

public class StartupUtil {
	
	public static void makeItWork(Server server) throws Exception {
		Method method = server.getClass().getDeclaredMethod("setPrimaryDevice", String.class);
		method.setAccessible(true);
//        method.invoke(server, "Seagate_50MB");
		method.invoke(server, "WD_100MB");

		Field monitorIdField = server.getClass().getDeclaredField("monitorId");
		monitorIdField.setAccessible(true);
		String monitorId = (String) monitorIdField.get(server);

		Field primaryMonitorField = server.getClass().getDeclaredField("primaryMonitor");
		primaryMonitorField.setAccessible(true);
		primaryMonitorField.set(server, monitorId);
	}
}
