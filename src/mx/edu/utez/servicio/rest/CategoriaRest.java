package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.CategoriaBean;
import mx.edu.utez.modelo.CategoriaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categoria")
public class CategoriaRest {

    private final CategoriaDao dao = new CategoriaDao() ;
    
    //Consulta 1 categoría por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoriaBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todas las categorías
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoriaBean> queryAll(){
        return dao.queryAll();
    }

    //Inserta 1 categoría
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(CategoriaBean bean){
        return dao.add(bean);
    }

    //Elimina 1 categoría por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@QueryParam("id") int id){
        return dao.delete(id);
    }

    //Actualiza 1 categoría por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(CategoriaBean bean){
        return dao.update(bean);
    }
}
