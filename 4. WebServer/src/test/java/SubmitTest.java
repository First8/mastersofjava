import static org.junit.Assert.assertEquals;

import nl.first8.moj.webserver.api.Api;
import nl.first8.moj.webserver.server.WebServer;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SubmitTest {

    private static final class Foo {

        private final String bar;
        private final String baz;

        public Foo(String bar, String baz) {
            this.bar = bar;
            this.baz = baz;
        }

        public String getBar() {
            return bar;
        }

        public String getBaz() {
            return baz;
        }
    }

    @Api.Resource("foo")
    public static final class FooResource {

        Map<String, String> foos = new HashMap<>();

        public FooResource() {
            foos.put("bar", "baz");
            foos.put("tata", "tete");
        }

        @Api.GET
        public Foo getFoo(@Api.Param("bar") String bar) {
            return new Foo(bar, foos.get(bar));
        }
    }

    @Api.Resource("person")
    static final class PersonResource {

    }

    @Api.Resource("person2")
    static final class Person2Resource {

    }

    @Test
    public void testHandleGetFoo() throws Exception {
        WebServer server = new WebServer(new HashSet<>(Arrays.asList(PersonResource.class, FooResource.class, Person2Resource.class)));

        final Foo tata = server.handle("foo", Collections.singletonMap("bar", "tata"));

        assertEquals(tata.getBar(), "tata");
        assertEquals(tata.getBaz(), "tete");
    }
}
