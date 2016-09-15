package com.vnet.resource;

import com.vnet.db.SqlService;
import com.vnet.model.Loinc;
import com.vnet.model.SimpleError;
import com.vnet.service.LoincService;
import com.vnet.service.ServiceException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@WebService
@Path("/codes")
@Produces(MediaType.APPLICATION_JSON)
public class LoincResource {

    final LoincService loincService = new LoincService(new SqlService());
    
    @WebMethod
    @GET
    @Path("/{id}")
    public Loinc getCode(@PathParam("id") String id) {
        try {
            return loincService.getCode(id);
        } catch (ServiceException e) {
            throw new WebApplicationException(makeResponse(e));
        }
    }

    @WebMethod
    @GET
    @Path("/")
    public Collection<Loinc> getCodes(
            @QueryParam("q") @DefaultValue("") String q,
            @QueryParam("type") @DefaultValue("code") String type)  {
        try {
            return loincService.getCodes(q, type);
        } catch (ServiceException e) {
            throw new WebApplicationException(makeResponse(e));
        }
    }

    static private Response makeResponse(ServiceException e) {
        final SimpleError error = e.asError();
        return Response.status(e.getCode().getStatus()).header("LOINC-HEADER", e.getMessage()).entity(error).build();
    }


}
