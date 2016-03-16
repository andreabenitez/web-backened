package servicios;

import modelos.Producto;
import modelos.ProductoDuplicado;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sonia on 14/03/16.
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductoDuplicadoServicios {
    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @EJB
    private ProductoServicios productoServicios;

    @EJB
    private ProductoDuplicadoServicios productoDuplicadoServicios;

    public void agregarListaDeProductos(List<Producto> productos)throws Exception{
        for (Producto producto : productos){
            productoServicios.agregarProducto(producto);
        }
    }

    public List<ProductoDuplicado> getProductosDuplicados() {
        Query query = entityManager.createNamedQuery("ProductoDuplicado.findAll");
        return query.getResultList();

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void agregarProductoDuplicado(Producto producto) throws Exception {

        ProductoDuplicado duplicado = productoDuplicadoServicios.buscarProductoDuplicadoPorProducto(producto);
        if (duplicado == null) {
            duplicado = new ProductoDuplicado();
            duplicado.setCodProducto(producto);
            duplicado.setCantidad(1);
            entityManager.persist(duplicado);
        } else {
            duplicado.setCantidad(duplicado.getCantidad() + 1);
            this.entityManager.merge(duplicado);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ProductoDuplicado buscarProductoDuplicadoPorProducto(Producto producto) {
        Query query = this.entityManager.createNamedQuery("ProductoDuplicado.findByCodProducto");
        query.setParameter("codProducto", producto);
        List<ProductoDuplicado> pro = query.getResultList();
        if (pro.size() > 0)
            return pro.get(0);
        else
            return null;
    }




}
