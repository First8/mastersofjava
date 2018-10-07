import static org.junit.Assert.assertEquals;

import nl.first8.moj.webserver.account.Account;
import nl.first8.moj.webserver.account.AccountResource;
import nl.first8.moj.webserver.api.Api;
import nl.first8.moj.webserver.server.WebServer;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GetAccountListTest {

    @Api.Resource("account2")
    static final class Account2Resource {

    }

    @Test
    public void testHandleAccountList() throws Exception {
        WebServer server = new WebServer(new HashSet<>(Arrays.asList(Account2Resource.class, AccountResource.class)));

        List<Account> accountList = server.handle("account", Collections.emptyMap());

        assertEquals(4, accountList.size());
        assertEquals(accountList.get(0).getUserName(), "m.reinhold");
        assertEquals(accountList.get(1).getUserName(), "b.goetz");
        assertEquals(accountList.get(2).getUserName(), "j.bloch");
        assertEquals(accountList.get(3).getUserName(), "d.lea");
    }
}
