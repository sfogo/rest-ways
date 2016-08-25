package com.vnet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    static private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static public String getenv(String name) {
        final String v = System.getenv(name);
        if (notSet(v))
            throw new IllegalArgumentException("Cannot find environment variable " + name);
        return v;
    }

    static public String getenv(String name, String defaultValue) {
        final String v = System.getenv(name);
        return notSet(v) ? defaultValue : v;
    }

    static public int getenv(String name, int defaultValue) {
        try {
            return Integer.parseInt(System.getenv(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    static public boolean notSet(String s) {return s==null || "".equals(s);}

    static public Date parse(String s) {
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    static public String formatDate(Date date) {
        return simpleDateFormat.format(date);
    }

    private static String ENV_PREFIX = "env:";
    static public String beanEnvProperty(String value) {
        return value.startsWith(ENV_PREFIX) ?
                getenv(value.substring(ENV_PREFIX.length())) :
                value;
    }
}
