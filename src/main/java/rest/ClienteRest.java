package rest;

import modelos.Cliente;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 27/02/16.
 */
@Path("/clientes")
public class ClienteRest {

    private static List<Cliente> clientes = new ArrayList<Cliente>();

    /**
     * Lista todos los clientes
     * @return List<Cliente>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() {

        clientes.add(new Cliente(1, "Benitez", "Andrea", "3969499", "andy.benitez09@gmail.com" ));
        clientes.add(new Cliente(2, "Quinonez", "Franciso", "3969500", "franqur17@gmail.com" ));
        clientes.add(new Cliente(3, "Laviosa", "Sonia", "3969501", "sonia.laviosa@gmail.com" ));

        return clientes;
    }

    /**
     * Crea un cliente
     * @param cliente
     * @return
     */
    @POST
    @Consumes("application/json")
    public Cliente crearCliente(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    /**
     * Retorna un cliente de acuerdo al id recibido como parametro
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Cliente buscarCliente(@PathParam("id") Integer id) {

        for (Cliente cliente : clientes){
            if (id.equals(cliente.getId())){
                return cliente;
            }
        }
        return null;
    }

    /**
     * Modifica un cliente
     * @param clienteModificado
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente modificarCliente(Cliente clienteModificado) {

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
