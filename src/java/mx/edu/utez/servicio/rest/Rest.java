package mx.edu.utez.servicio.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.edu.utez.servicio.modelo.RolDao;
import org.json.JSONObject;

@Path("/Rest")
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

    //Inicio Rol

    //Consulta 1 rol
    @GET
    @Path("rol/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response query(@QueryParam("id") int id){
        JSONObject json = new JSONObject();
        RolDao dao = new RolDao();

        json.put("rol", dao.query(id));

        return Response.ok( json.toString() ).build();
    }

    //Consulta todos los roles
    @GET
    @Path("rol/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response queryAll(){
        JSONObject json = new JSONObject();
        RolDao dao = new RolDao();

        json.put("roles", dao.queryAll());

        return Response.ok( json.toString() ).build();
    }

    //Fin Rol

}
