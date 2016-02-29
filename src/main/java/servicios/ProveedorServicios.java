package servicios;


import modelos.Producto;
import modelos.Proveedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class ProveedorServicios {

    private static HashMap<Integer, Proveedor> proveedores = new HashMap<>();
    private static int id =0;


    public static List<Proveedor> getProveedores() {
        if(proveedores.size() ==0 ){
            proveedores.put(++id, new Proveedor(id, "COCA", "Fdo de la Mora", "0981888642"));
            proveedores.put(++id, new Proveedor(id, "Pepsi", "Asuncion", "7897456"));
            proveedores.put(++id, new Proveedor(id, "Pulp", "Lambare", "789120"));
        }
        List<Proveedor> proveedorList = new ArrayList<Proveedor>(proveedores.values());
        return proveedorList;
    }

    public static Proveedor agregarProveedor(Proveedor proveedor){
        proveedor.setId(++id);
        proveedores.put(id, proveedor);
        return proveedor;
    }

    public static void eliminarProveedor(Integer proveedorId){
        List<Producto> productoList = ProductoServicios.getProductos();
        Proveedor proveedor = proveedores.get(proveedorId);
        for (Producto producto : productoList){
            if (producto.getProveedor().equals(proveedor)){
                producto.setProveedor(null);
            }
        }
        proveedores.remove(proveedorId);
    }


    public static Proveedor buscarProveedor(Integer proveedorId){

        return proveedores.get(proveedorId);
    }

    public static void modificarProveedor(Proveedor proveedor){
        proveedores.put(proveedor.getId(), proveedor);
    }
}
