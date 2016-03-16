package servicios;

import excepciones.NoExisteProductoException;
import excepciones.NoExisteProveedorException;
import modelos.Compra;
import modelos.CompraDetalle;
import modelos.Producto;
import modelos.Proveedor;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class CompraServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProductoServicios productoServicios;

    @Inject
    private ProveedorServicios proveedorServicios;


    public List<Compra> getCompras() {
        return entityManager.createNamedQuery("Cliente.findAll").getResultList();
    }

    public Response agregarCompra(Compra compra) throws EJBTransactionRolledbackException {

        int error = 0;
        try {
            error = crearCompra(compra);
            if (error == 1) {
                return Response.status(500).entity("No hay ningun detalle en la compra").build();
            } else if (error == 2) {
                return Response.status(500).entity("Hay productos que no pertenecen al proveedor selecionado").build();
            } else {
                return Response.status(200).entity("La compra fue realizada exitosamente").build();
            }
        } catch (Exception e) {
            return Response.status(500).entity("Ha ocurrido un error durante el proceso de compra: " + e.getMessage()).build();
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int crearCompra(Compra compra) throws NoExisteProveedorException, NoExisteProductoException {


        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        compra.setDate(dateString);
        Float total = new Float(0.0);

        Proveedor proveedor = proveedorServicios.buscarProveedor(compra.getProveedor().getIdProveedor());
        compra.setProveedor(proveedor);

        if (compra.getCompraDetalles().isEmpty()) {
            return 1;
        }

        for (CompraDetalle compraDetalle : compra.getCompraDetalles()) {
            Producto producto = productoServicios.buscarProducto(compraDetalle.getProducto().getIdProducto());
            if (!(producto.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor()))) {
                return 2;
                //return Response.status(500).entity("El producto" + producto.toString() + "no pertenece al proveedor seleccionado: " + compra.getProveedor().toString() + "\nNo se pudo realizar la compra").build() ;
            }
        }

        for (CompraDetalle compraDetalle : compra.getCompraDetalles()) {
            compraDetalle.setCompra(compra);
            Producto producto = productoServicios.buscarProducto(compraDetalle.getProducto().getIdProducto());
            producto.setCantidad(producto.getCantidad() + compraDetalle.getCantidad());
            entityManager.merge(producto);
            total = total + ((producto.getPrecioUnitario() * compraDetalle.getCantidad()));
            entityManager.persist(compraDetalle);
        }

        compra.setTotal(total);
        entityManager.persist(compra);
        return 0;

    }

}
