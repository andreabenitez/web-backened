package servicios;

import modelos.Cliente;
import excepciones.TamanoPaginaExcepcion;
import modelos.Producto;
import modelos.ProductoDuplicado;
import modelos.Proveedor;
import paginacion.Paginacion;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductoServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProveedorServicios proveedorServicios;

    @EJB
    private ProductoServicios productoServicios;

    @Inject
    private ProductoDuplicadoServicios productoDuplicadoServicios;

    public  List<Producto> getProductos(Paginacion paginacion) throws TamanoPaginaExcepcion {
        if (paginacion.getTamanoPagina() > 100){
            throw new TamanoPaginaExcepcion("El servicio no permite responder a solicitudes mayores a 100");
        }
        else {
            int primerRegistro = (paginacion.getTamanoPagina() * (paginacion.getNumeroPagina() - 1));
            Query query = entityManager.createNamedQuery("Producto.findAll");
            return query
                    .setFirstResult(primerRegistro)
                    .setMaxResults(paginacion.getTamanoPagina())
                    .getResultList();
        }
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Response agregarProducto(Producto producto) throws Exception {
        try {
            Proveedor proveedor = proveedorServicios.buscarProveedor(producto.getProveedor().getIdProveedor());
            producto.setProveedor(proveedor);
            entityManager.persist(producto);
            return Response.status(200).entity("La insercion fue realizada exitosamente").build();

        } catch (Exception e) {
            Producto p = productoServicios.buscarProductoPorNombre(producto);
            productoDuplicadoServicios.agregarProductoDuplicado(p);
            return Response.status(500).entity("Ha ocurrido un error durante el proceso: " + e.getMessage()).build();

        }
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Producto buscarProductoPorNombre(Producto producto) {

        Query query = entityManager.createNamedQuery("Producto.findByNombre");
        query.setParameter("nombre", producto.getNombre());
        List<Producto> pro = query.getResultList();
        return pro.get(0);
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
    public Producto buscarProducto(Integer productoId) {
        Producto producto = entityManager.find(Producto.class, productoId);
        return producto;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Producto modificarProducto(Producto producto) {
        Proveedor proveedor = proveedorServicios.buscarProveedor(producto.getProveedor().getIdProveedor());
        producto.setProveedor(proveedor);
        return entityManager.merge(producto);
    }
}
