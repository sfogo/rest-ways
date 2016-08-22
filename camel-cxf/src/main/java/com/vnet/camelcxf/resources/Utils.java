package com.vnet.camelcxf.resources;

import javax.ws.rs.core.Response;
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

    static public boolean notSet(String s) {return s==null || "".equals(s);}

    static public Response makeResponse(int status, String id, String message) {
        final ErrorDetails details = new ErrorDetails();
        details.setId(id);
        details.setMessage(message);
        return Response.status(status).header("LOINC-HEADER", message + ":[" + id + "]").entity(details).build();
    }

    static Date parse(String s) {
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }
}
