package mx.edu.utez.servicio.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.edu.utez.modelo.RolDao;
import org.json.JSONObject;

@Path("/rol")
public class RolRest {

    private JSONObject json ;
    private RolDao dao = new RolDao();

    //Consulta 1 rol
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response query(@QueryParam("id") int id){
        json = new JSONObject();
        json.put("rol", dao.query(id));

        return Response.ok( json.toString() ).build();
    }

    //Consulta todos los roles
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response queryAll(){
        json = new JSONObject();
        json.put("roles", dao.queryAll());

        return Response.ok( json.toString() ).build();
    }


}
