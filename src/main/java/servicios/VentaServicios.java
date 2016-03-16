package servicios;

import excepciones.NoExisteProductoException;
import excepciones.ValidarExistenciaDetalle;
import excepciones.ValidarExistenciaStockException;
import modelos.*;
import org.hibernate.exception.ConstraintViolationException;

import javax.annotation.Resource;
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

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProductoServicios productoServicios;

    @Inject
    private ClienteServicios clienteServicios;

    public List<Venta> getVentas() {
        return entityManager.createNamedQuery("Venta.findAll").getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response agregarVenta(Venta venta) {
        try {
            Cliente cliente = clienteServicios.buscarCliente(venta.getCliente().getIdCliente());
            venta.setCliente(cliente);
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            venta.setDate(dateString);
            Float total = new Float(0.0);
            List<Producto> productoModificadoList = new ArrayList<>();
            validarExistenciaDetalleVenta(venta);
            validarExistenciaStock(venta);

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

        } catch (Exception e) {
            String errorMessage = "La venta no se pudo realizar: ";
            if (e instanceof ValidarExistenciaStockException  ||
                    e instanceof ValidarExistenciaDetalle){
                errorMessage += e.getMessage();
            }
            else if (e.getCause() instanceof ConstraintViolationException ){
                context.setRollbackOnly();
                errorMessage = e.getCause().getMessage();

            }
            else if(e instanceof NoExisteProductoException){
                errorMessage += e.getMessage();
            }

            return Response.status(500).entity(errorMessage).build();
        }
    }

    public Venta modificarVenta(Venta venta) {
        return entityManager.merge(venta);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Venta buscarVentaPorId(Integer idVenta) {
        return entityManager.find(Venta.class, idVenta);
    }

    private boolean validarExistenciaStock(Venta venta) throws ValidarExistenciaStockException, NoExisteProductoException {
        for (VentaDetalle ventaDetalle : venta.getVentaDetalles()) {
            Producto producto = productoServicios.buscarProducto(ventaDetalle.getProducto().getIdProducto());
            if (!(producto.getCantidad() >= ventaDetalle.getCantidad())) {
                throw new ValidarExistenciaStockException("El producto: " + producto.getNombre() + "no cuenta con suficiente stock");
            }
        }
        return true;
    }

    private boolean validarExistenciaDetalleVenta(Venta venta) throws ValidarExistenciaDetalle {
        if (venta.getVentaDetalles().size() > 0){
            return true;
        }else{
            throw new ValidarExistenciaDetalle("La venta no posee detalles. Intente de vuelta");
        }
    }
}
