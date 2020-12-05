package mx.edu.utez.servicio.rest;

import mx.edu.utez.modelo.Carrito_JugueteBean;
import mx.edu.utez.modelo.Carrito_JugueteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/carrito_juguete")
public class Carrito_JugueteRest {

    private final Carrito_JugueteDao dao = new Carrito_JugueteDao();

    //Inserta 1 juguete a un carrito
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(Carrito_JugueteBean bean){
        return dao.add(bean);
    }

    //Consulta todas las Juguetes de un carrito
    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrito_JugueteBean> queryAll(@QueryParam("idCarrito") int idCarrito){
        return dao.history(idCarrito);
    }

    //Actualiza la cantidad de juguetes a comprar en un carrito
    @PUT
    @Path("/updateCantidad")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean buy(Carrito_JugueteBean bean){
        return dao.updateCantidad(bean);
    }

    //Elimina 1 Juguete del carrito
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean delete(Carrito_JugueteBean bean){
        return dao.delete(bean);
    }
}
