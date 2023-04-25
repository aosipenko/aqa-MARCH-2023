package org.prog.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DataHolder {
    private final static HashMap<String, Object> holder = new HashMap<>();

    public void put(String alias, Object o) {
        holder.put(alias, o);
    }

    public Object get(String alias) {
        return holder.get(alias);
    }
}
