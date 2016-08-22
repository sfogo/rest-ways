package com.vnet.camelrest.service;

public class Utils {
    private static String ENV_PREFIX = "env:";

    static public String property(String value) {
        return value.startsWith(ENV_PREFIX) ?
            getenv(value.substring(ENV_PREFIX.length())) :
            value;
    }

    static private String getenv(String name) {
        final String v = System.getenv(name);
        if (notSet(v))
            throw new IllegalArgumentException("Cannot find environment variable " + name);
        return v;
    }

    static public boolean notSet(String s) {return s==null || "".equals(s);}

}
