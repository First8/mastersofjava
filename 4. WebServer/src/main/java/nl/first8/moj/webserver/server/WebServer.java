package nl.first8.moj.webserver.server;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import nl.first8.moj.webserver.api.Api;

public class WebServer {

    private final Set<Class<?>> resources;

    public WebServer(Set<Class<?>> resources) {
        this.resources = Collections.unmodifiableSet(new HashSet<>(resources));
    }

    public <T> T handle(String endPoint, Map<String, Object> parameters) throws Exception {
        throw new UnsupportedOperationException("Implement!");
    }
    
    private Class<?> getRequestedEndpoint(String endPoint) throws NoSuchElementException {
        return resources.stream()
                .filter(res -> res.getAnnotation(Api.Resource.class).value().equalsIgnoreCase(endPoint))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
