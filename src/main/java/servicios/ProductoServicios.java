package servicios;

import excepciones.TamanoPaginaExcepcion;
import modelos.Cliente;
import modelos.Producto;
import modelos.Proveedor;
import paginacion.Paginacion;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public  Producto agregarProducto(Producto producto){
        Proveedor proveedor = proveedorServicios.buscarProveedor(producto.getProveedor().getIdProveedor());
        producto.setProveedor(proveedor);
        entityManager.persist(producto);
        return producto;
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
