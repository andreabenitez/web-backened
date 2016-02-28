/*
package ejb;

import modelos.Cliente;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by andrea on 27/02/16.
 *//*


@Stateless
@LocalBean
public class ClienteManager {

    @EJB
    ClienteManager clienteManager;

    List<Cliente> clientes = new ArrayList<Cliente>();


    public List<Cliente> listarClientes() {

        clientes.add(new Cliente(1, "Benitez", "Andrea", "3969499", "andy.benitez09@gmail.com" ));
        clientes.add(new Cliente(1, "Quinonez", "Franciso", "3969500", "franqur17@gmail.com" ));
        clientes.add(new Cliente(1, "Laviosa", "Sonia", "3969501", "sonia.laviosa@gmail.com" ));

        return clientes;
    }

    public Cliente crearCliente(Cliente cliente){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(cliente);
        return cliente;
    }

    public Cliente buscarClienteConId(Integer id){
        List<Cliente> clientes = new ArrayList<Cliente>();
        for (Cliente cliente : clientes){
            if (id.equals(cliente.getId())){
                return cliente;
            }
        }
        return null;
    }

    public Cliente modificarCliente(Cliente clienteModificado){
        List<Cliente> clientes = new ArrayList<Cliente>();
        for(Cliente cliente : clientes){
            if (cliente.getId().equals(clienteModificado.getId())){
                clientes.remove(cliente);
                clientes.add(clienteModificado);
                return clienteModificado;
            }
        }
        return  null;

    }
}
*/
