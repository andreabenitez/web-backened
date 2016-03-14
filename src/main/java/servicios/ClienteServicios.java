package servicios;

import modelos.Cliente;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by andrea on 29/02/16.
 */

@Stateless
public class ClienteServicios {

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    public  List<Cliente> getClientes() {
        Query query = entityManager.createNamedQuery("Cliente.findAll");
        return query.getResultList();
    }

    public  Cliente agregarCliente(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    public String eliminarCliente(Integer clienteId){
        Cliente client = entityManager.find(Cliente.class, clienteId);
        if (null != client) {
            entityManager.remove(client);
            return "Cliente eliminado exitosamente";
        }
        return "El cliente que intenta eliminar no exite";
    }


    public  Cliente buscarCliente(Integer clienteId){
        Cliente client = entityManager.find(Cliente.class, clienteId);
        return client;
    }

    public Cliente modificarCliente(Cliente cliente){
        return entityManager.merge(cliente);
    }
}
