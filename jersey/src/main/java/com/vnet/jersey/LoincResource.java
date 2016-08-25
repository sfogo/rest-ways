package com.vnet.jersey;

import com.vnet.db.SqlService;
import com.vnet.model.ErrorDetails;
import com.vnet.model.Loinc;
import com.vnet.service.LoincService;
import com.vnet.service.ServiceException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("loinc")
public class LoincResource {

    private LoincService service = new LoincService(new SqlService());

    @GET
    @Path("/codes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Loinc getCode(@PathParam("id") String id) {
        try {
            return service.getCode(id);
        } catch (ServiceException e) {
            throw new WebApplicationException(makeResponse(e));
        }
    }

    @GET
    @Path("/codes/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Loinc> getCodes(
            @QueryParam("q") String q,
            @QueryParam("type") String type) {
        try {
            return service.getCodes(q, type);
        } catch (ServiceException e) {
            throw new WebApplicationException(makeResponse(e));
        }
    }

    static private Response makeResponse(ServiceException e) {
        final ErrorDetails details = e.asErrorDetails();
        return Response.status(e.getCode().getStatus()).header("LOINC-HEADER", e.getMessage()).entity(details).build();
    }
}
