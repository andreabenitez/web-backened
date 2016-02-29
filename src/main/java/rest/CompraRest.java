package rest;



import modelos.Compra;
import servicios.CompraServicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */

@Path("/compras")
public class CompraRest {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compra> listarCompras() {
        return CompraServicios.getCompras();
    }


    @POST
    @Consumes("application/json")
    public Response crearCompra(Compra compra) {
        return CompraServicios.agregarCompra(compra);
    }
}
