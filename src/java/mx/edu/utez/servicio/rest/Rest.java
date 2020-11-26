package mx.edu.utez.servicio.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/jugueteria")
public class Rest {

    //Para probar que REST funcione
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(){
         JSONObject json = new JSONObject();
         json.put("res", "El servicio REST esta funcionando!");
         return Response.ok( json.toString() ).build();
    }
}
