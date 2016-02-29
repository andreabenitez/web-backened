package servicios;

import modelos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class ProductoServicios {

    private static HashMap<Integer, Producto> productos= new HashMap<>();
    private static int id =0;

    public static List<Producto> getProductos() {

        if(productos.size() == 0){
            productos.put(++id, new Producto(id, "coca cola", 5, new Float(5000), "de medio litro", ProveedorServicios.buscarProveedor(1)));
            productos.put(++id, new Producto(id, "pepsi cola", 5, new Float(5500), "de medio litro", ProveedorServicios.buscarProveedor(2)));
            productos.put(++id, new Producto(id, "pulp cola", 5, new Float(4500), "de medio litro", ProveedorServicios.buscarProveedor(3)));
        }
        List<Producto> proveedorList = new ArrayList<Producto>(productos.values());
        return proveedorList;
    }

    public static Producto agregarProducto(Producto producto){
        producto.setId(++id);
        productos.put(id, producto);
        return producto;
    }

    public static void eliminarProducto(Integer productoId){
        productos.remove(productoId);
    }


    public static Producto buscarProducto(Integer proveedorId){
        return productos.get(proveedorId);
    }

    public static void modificarProducto(Producto producto){
        productos.put(producto.getId(), producto);
    }
}
