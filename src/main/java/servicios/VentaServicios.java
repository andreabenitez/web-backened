package servicios;

import modelos.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Stateless
public class VentaServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProductoServicios productoServicios;

    public List<Venta> getVentas() {
        return entityManager.createNamedQuery("Venta.findAll").getResultList();
    }

    public Response agregarVenta(Venta venta){

        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        venta.setDate(dateString);
        Float total = new Float(0.0);
        List<Producto> productoModificadoList = new ArrayList<>();

        for(VentaDetalle ventaDetalle : venta.getVentaDetalles()){
            Producto producto = productoServicios.buscarProducto(ventaDetalle.getProducto().getIdProducto());
            if(!(producto.getCantidad() >= ventaDetalle.getCantidad())) {
                return Response.status(500).entity("Insuficiencia en stock del producto :" + producto.toString()).build();
            }
        }

        for(VentaDetalle ventaDetalle : venta.getVentaDetalles()){
            Producto producto = productoServicios.buscarProducto(ventaDetalle.getProducto().getIdProducto());
            producto.setCantidad(producto.getCantidad() - ventaDetalle.getCantidad());
            productoServicios.modificarProducto(producto);
            total = total + (producto.getPrecioUnitario() * ventaDetalle.getCantidad())* new Float(1.1);
        }


        venta.setTotal(total);
        venta.setSaldoDeuda(total);
        entityManager.persist(venta);
        return Response.status(200).entity(venta.toString()).build();
    }

    public Venta modificarVenta(Venta venta){
        return entityManager.merge(venta);
    }

    public Venta buscarVentaPorId (Integer idVenta){
        return entityManager.find(Venta.class,idVenta);
    }
}
