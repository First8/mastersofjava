import static org.junit.Assert.assertEquals;

import nl.first8.moj.webserver.account.Account;
import nl.first8.moj.webserver.account.AccountResource;
import nl.first8.moj.webserver.api.Api;
import nl.first8.moj.webserver.server.WebServer;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class GetSingleAccountTest {

    @Api.Resource("account2")
    static final class Account2Resource {

    }

    @Test
    public void testHandleGetSingleAccount() throws Exception {
        WebServer server = new WebServer(new HashSet<>(Arrays.asList(Account2Resource.class, AccountResource.class)));

        Account mreinhold = server.handle("account", Collections.singletonMap("id", 1));
        assertEquals(mreinhold.getId(), 1);
        assertEquals(mreinhold.getUserName(), "m.reinhold");

        Account dlea = server.handle("account", Collections.singletonMap("id", 3));
        assertEquals(dlea.getId(), 3);
        assertEquals(dlea.getUserName(), "j.bloch");
    }
}
