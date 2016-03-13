package servicios;

import modelos.Cliente;
import modelos.Producto;
import modelos.Proveedor;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Stateless
@LocalBean
public class ProductoServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProveedorServicios proveedorServicios;

    public  List<Producto> getProductos() {
        Query query = entityManager.createNamedQuery("Producto.findAll");
        return query.getResultList();
    }

    public  Response agregarProducto(Producto producto) throws Exception{
        try {
            Proveedor proveedor = proveedorServicios.buscarProveedor(producto.getProveedor().getIdProveedor());
            producto.setProveedor(proveedor);
            entityManager.persist(producto);
            return Response.status(200).entity("La insercion fue realizada exitosamente").build();

        }catch (Exception e){
            return Response.status(500).entity("Ha ocurrido un error durante el proceso: " + e.getMessage()).build();

        }

    }

    public String eliminarProducto(Integer productoId){
        Producto producto = entityManager.find(Producto.class, productoId);
        if (null != producto) {
            entityManager.remove(producto);
            return "Producto eliminado exitosamente";
        }
        return "El Producto que intenta eliminar no exite";
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public  Producto buscarProducto(Integer productoId){
        Producto producto = entityManager.find(Producto.class, productoId);
        return producto;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Producto modificarProducto(Producto producto){
        Proveedor proveedor = proveedorServicios.buscarProveedor(producto.getProveedor().getIdProveedor());
        producto.setProveedor(proveedor);
        return entityManager.merge(producto);
    }
}
