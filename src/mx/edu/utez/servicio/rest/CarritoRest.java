package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.CarritoBean;
import mx.edu.utez.modelo.CarritoDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/carrito")
public class CarritoRest {

    private final CarritoDao dao = new CarritoDao();

    //Inserta 1 Carrito
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(CarritoBean bean){
        return dao.add(bean.getPersona().getId());
    }

    //Compra 1 Carrito por id
    @PUT
    @Path("/buy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean buy(CarritoBean bean){
        return dao.buy(bean.getId());
    }

    //Consulta el carrito activo de una persona
    @GET
    @Path("/queryActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public CarritoBean queryActivo(@QueryParam("idPersona") int idPersona){
        return dao.queryActivo(idPersona);
    }

    //Consulta todas las Carritos
    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarritoBean> queryAll(@QueryParam("id") int idPersona){
        return dao.queryAll(idPersona);
    }
}
