package mx.edu.utez.servicio.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import mx.edu.utez.modelo.RolBean;
import mx.edu.utez.modelo.RolDao;

import java.util.List;

@Path("/rol")
public class RolRest {

    private final RolDao dao = new RolDao();

    //Consulta 1 rol
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RolBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todos los roles
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RolBean> queryAll(){
        return dao.queryAll();
    }
}
