package org.prog.util;

import java.util.HashMap;

public class DataHolder {

    private final static HashMap<String, Object> holder = new HashMap<>();
    private final static DataHolder instance = new DataHolder();

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return instance;
    }

    public void put(String alias, Object o) {
        holder.put(alias, o);
    }

    public Object get(String alias) {
        return holder.get(alias);
    }
}
