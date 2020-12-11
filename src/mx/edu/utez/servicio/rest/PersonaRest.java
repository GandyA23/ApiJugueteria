package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.PersonaDao;
import mx.edu.utez.modelo.PersonaBean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persona")
public class PersonaRest {
    private final PersonaDao dao = new PersonaDao() ;

    //Consulta 1 persona por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaBean query(@QueryParam("id") int id){
        return dao.query(id);
    }

    //Consulta todas las personas
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonaBean> queryAll(){
        return dao.queryAll();
    }

    //Inicio de sesión
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaBean login(@QueryParam("email") String email, @QueryParam("password") String password){
        return dao.login(email, password);
    }

    //Inserta 1 persona y retorna su id en la DB,
    //en caso de que retorne 0, quiere decir que no se ingreso
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int add(PersonaBean bean){
        return dao.add(bean);
    }

    //Elimina 1 persona por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@QueryParam("id") int id){
        return dao.delete(id);
    }

    //Actualiza 1 persona por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(PersonaBean bean){
        return dao.update(bean);
    }

    //Actualiza la password de una persona por id
    @PUT
    @Path("/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updatePassword(PersonaBean bean){
        return dao.updatePassword(bean);
    }
}
