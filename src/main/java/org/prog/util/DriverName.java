package org.prog.util;

public enum DriverName {
    CHROME_LOCAL, CHROME_REMOTE, CHROME_JENKINS;

    public static DriverName byName(String name) {
        try {
            return DriverName.valueOf(name);
        } catch (Exception e) {
            return CHROME_JENKINS;
        }
    }
}
