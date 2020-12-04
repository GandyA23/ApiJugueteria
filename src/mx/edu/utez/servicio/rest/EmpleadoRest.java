package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.EmpleadoBean;
import mx.edu.utez.modelo.EmpleadoDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/empleado")
public class EmpleadoRest {
    private final EmpleadoDao dao = new EmpleadoDao() ;

    //Consulta 1 Empleado por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public EmpleadoBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todas las Empleados
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmpleadoBean> queryAll(){
        return dao.queryAll();
    }

    //Inserta 1 Empleado
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(EmpleadoBean bean){
        return dao.add(bean);
    }

    //Elimina 1 Empleado por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@QueryParam("id") int id){
        return dao.delete(id);
    }

    //Actualiza 1 Empleado por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(EmpleadoBean bean){
        return dao.update(bean);
    }
}
