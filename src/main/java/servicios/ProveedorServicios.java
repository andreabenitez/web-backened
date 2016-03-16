package servicios;


import excepciones.NoExisteProveedorException;
import modelos.Producto;
import modelos.Proveedor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Stateless
public class ProveedorServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    public List<Proveedor> getProveedores() {
        Query query = entityManager.createNamedQuery("Proveedor.findAll");
        return query.getResultList();
    }

    public Proveedor agregarProveedor(Proveedor proveedor){
        entityManager.persist(proveedor);
        return proveedor;
    }

    public String eliminarProveedor(Integer proveedorId){
        Proveedor proveedor = entityManager.find(Proveedor.class, proveedorId);
        if(null != proveedor){
            entityManager.remove(proveedor);
            return "Proveedor eliminado exitosamente.";
        }
        return "El proveedor que intenta eliminar no existe";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public  Proveedor buscarProveedor(Integer proveedorId) throws NoExisteProveedorException {
        Proveedor proveedor = entityManager.find(Proveedor.class, proveedorId);
        if (proveedor == null){
            throw new NoExisteProveedorException("No existe proveedor con id:" + proveedorId);
        }else{
            return proveedor;
        }
    }

    public Proveedor modificarProveedor(Proveedor proveedor){
        return entityManager.merge(proveedor);
    }
}
