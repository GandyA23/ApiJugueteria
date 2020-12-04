package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.JugueteBean;
import mx.edu.utez.modelo.JugueteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/juguete")
public class JugueteRest {
    private final JugueteDao dao = new JugueteDao() ;

    //Consulta 1 Juguete por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public JugueteBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todas las Juguetes
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JugueteBean> queryAll(){
        return dao.queryAll();
    }

    //Inserta 1 Juguete
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(JugueteBean bean){
        return dao.add(bean);
    }

    //Elimina 1 Juguete por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@QueryParam("id") int id){
        return dao.delete(id);
    }

    //Actualiza 1 Juguete por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(JugueteBean bean){
        return dao.update(bean);
    }
}
