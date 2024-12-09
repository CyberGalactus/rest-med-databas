package org.example.restmeddatabas;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.restmeddatabas.model.Edu;

@Path("/edu")
public class EdusResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Edu hello() {
        return new Edu("Edu", 53);
    }



}
