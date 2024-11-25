import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ServerTest {

    @Test
    public void boot() throws Exception {

        Server server = new Server();

        server.connectPeripherals();

        StartupUtil.makeItWork(server);

        server.boot();

        assertTrue(server.isHudsonRunning());
    }

}
