package servicios;

import modelos.*;

import javax.ejb.*;
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
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VentaServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProductoServicios productoServicios;

    public List<Venta> getVentas() {
        return entityManager.createNamedQuery("Venta.findAll").getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response agregarVenta(Venta venta){
        try {
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            venta.setDate(dateString);
            Float total = new Float(0.0);
            List<Producto> productoModificadoList = new ArrayList<>();

            for (VentaDetalle ventaDetalle : venta.getVentaDetalles()) {
                Producto producto = productoServicios.buscarProducto(ventaDetalle.getProducto().getIdProducto());
                if (!(producto.getCantidad() >= ventaDetalle.getCantidad())) {
                    return Response.status(500).entity("Insuficiencia en stock del producto :" + producto.toString()).build();
                }
            }

            for (VentaDetalle ventaDetalle : venta.getVentaDetalles()) {
                ventaDetalle.setVenta(venta);
                Producto producto = productoServicios.buscarProducto(ventaDetalle.getProducto().getIdProducto());
                producto.setCantidad(producto.getCantidad() - ventaDetalle.getCantidad());
                entityManager.merge(producto);
                total = total + (producto.getPrecioUnitario() * ventaDetalle.getCantidad()) * new Float(1.1);
                ventaDetalle.setProducto(producto);
                entityManager.persist(ventaDetalle);
            }


            venta.setTotal(total);
            venta.setSaldoDeuda(total);
            entityManager.persist(venta);
            return Response.status(200).entity(venta.toString()).build();
        }catch(Exception e){
            return Response.status(500).entity("La venta no se pudo realizar" + e.getMessage()).build();
        }
    }

    public Venta modificarVenta(Venta venta){
        return entityManager.merge(venta);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Venta buscarVentaPorId (Integer idVenta){
        return entityManager.find(Venta.class,idVenta);
    }
}
