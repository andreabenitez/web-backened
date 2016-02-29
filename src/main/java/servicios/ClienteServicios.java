package servicios;

import modelos.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class ClienteServicios {

    private static HashMap<Integer, Cliente> clientes = new HashMap<>();
    private static int id =0;


    public static List<Cliente> getClientes() {
        if (clientes.size() == 0){
            clientes.put(++id, new Cliente(id, "Benitez", "Andrea", "3969499", "andy.benitez09@gmail.com" ));
            clientes.put(++id, new Cliente(id, "Quinonez", "Franciso", "3969500", "franqur17@gmail.com" ));
            clientes.put(++id, new Cliente(id, "Laviosa", "Sonia", "3969501", "sonia.laviosa@gmail.com" ));
        }
        List<Cliente> clienteList = new ArrayList<Cliente>(clientes.values());
        return clienteList;
    }

    public static Cliente agregarCliente(Cliente cliente){
        cliente.setId(++id);
        clientes.put(id, cliente);
        return cliente;
    }

    public static void eliminarCliente(Integer clienteId){
        clientes.remove(clienteId);
    }


    public static Cliente buscarCliente(Integer clienteId){
        return clientes.get(clienteId);
    }

    public static void modificarCliente(Cliente cliente){
        clientes.put(cliente.getId(), cliente);
    }
}
