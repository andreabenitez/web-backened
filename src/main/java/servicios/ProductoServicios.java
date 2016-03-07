package servicios;

import modelos.Cliente;
import modelos.Producto;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

    public  List<Producto> getProductos() {
        Query query = entityManager.createNamedQuery("Producto.findAll");
        return query.getResultList();
    }

    public  Producto agregarProducto(Producto producto){
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


    public  Producto buscarProducto(Integer productoId){
        Producto producto = entityManager.find(Producto.class, productoId);
        return producto;
    }

    public Producto modificarProducto(Producto producto){
        return entityManager.merge(producto);
    }
}
