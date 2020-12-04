package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.ClienteBean;
import mx.edu.utez.modelo.ClienteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cliente")
public class ClienteRest {
    private final ClienteDao dao = new ClienteDao() ;

    //Consulta 1 cliente por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todas las clientes
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteBean> queryAll(){
        return dao.queryAll();
    }

    //Inserta 1 cliente
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(ClienteBean bean){
        return dao.add(bean);
    }

    //Elimina 1 cliente por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@QueryParam("id") int id){
        return dao.delete(id);
    }

    //Actualiza 1 cliente por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(ClienteBean bean){
        return dao.update(bean);
    }
}
