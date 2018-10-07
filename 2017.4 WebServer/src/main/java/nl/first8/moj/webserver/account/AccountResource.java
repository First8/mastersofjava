package nl.first8.moj.webserver.account;

import nl.first8.moj.webserver.api.Api;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Api.Resource("account")
public final class AccountResource {

    private final Map<Integer, String> accounts;

    public AccountResource() {
        accounts = new HashMap<>();
        accounts.put(1, "m.reinhold");
        accounts.put(2, "b.goetz");
        accounts.put(3, "j.bloch");
        accounts.put(4, "d.lea");
    }

    @Api.GET
    public Account get(@Api.Param("id") final Integer id) {
        return get(id.intValue());
    }

    @Api.GET
    public List<Account> get() {
        return accounts.entrySet()
                .stream()
                .map(el -> new Account(el.getKey(), el.getValue()))
                .sorted(Comparator.comparing(Account::getId))
                .collect(Collectors.toList());
    }

    private Account get(final int id) {
        if (!accounts.containsKey(id)) {
            throw new NoSuchElementException("No account with id: " + id);
        }

        return new Account(id, accounts.get(id));
    }
}
