package com.group3.ssdk;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the {@link AuthRequestStore} interface, that stores authentication
 * requests in memory backed by a {@code HashMap}.
 */
public class MemoryAuthRequestStore implements AuthRequestStore
{

    /**
     * The requests stored by the authentication request store.
     */
    private final Map<String, AuthRequest> requests = new HashMap<>();

    @Override public AuthRequest store(AuthRequest request)
    {
        return requests.put(request.getId(), request);
    }

    @Override public boolean has(String id)
    {
        return requests.containsKey(id);
    }

    @Override public AuthRequest get(String id)
    {
        return requests.get(id);
    }

    @Override public AuthRequest delete(String id)
    {
        return requests.remove(id);
    }
}
