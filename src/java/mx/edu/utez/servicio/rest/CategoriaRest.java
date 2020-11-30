package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.CategoriaBean;
import mx.edu.utez.modelo.CategoriaDao;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categoria")
public class CategoriaRest {
    
    private JSONObject json ;
    private CategoriaDao dao = new CategoriaDao() ;
    
    //Consulta 1 categoría por id
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response query(@QueryParam("id") int id){
        json = new JSONObject();
        json.put("categoria", dao.query(id));

        return Response.ok( json.toString() ).build();
    }

    //Consulta todas las categorías
    @GET
    @Path("/queryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response queryAll(){
        json = new JSONObject();
        json.put("categorias", dao.queryAll());

        return Response.ok( json.toString() ).build();
    }

    //Inserta 1 categoría
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(CategoriaBean bean){
        json = new JSONObject();
        json.put("flag", dao.add(bean));

        return Response.ok( json.toString() ).build();
    }

    //Elimina 1 categoría por id
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") int id){
        json = new JSONObject();
        json.put("flag", dao.delete(id));

        return Response.ok( json.toString() ).build();
    }

    //Actualiza 1 categoría por id
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CategoriaBean bean){
        json = new JSONObject();
        json.put("flag", dao.update(bean));

        return Response.ok( json.toString() ).build();
    }


}
