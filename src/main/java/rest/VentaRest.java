package rest;

import modelos.Venta;
import servicios.VentaServicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Path("/ventas")
public class VentaRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venta> listarVentas() {
        return VentaServicios.getVentas();
    }


    @POST
    @Consumes("application/json")
    public Response crearVenta(Venta venta) {
        return VentaServicios.agregarVenta(venta);
    }
}
