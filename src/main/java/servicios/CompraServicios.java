package servicios;




import modelos.Compra;
import modelos.CompraDetalle;
import modelos.Producto;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class CompraServicios {

    private static HashMap<Integer, Compra> compras = new HashMap<>();

    private static int idCompra =0;
    private static int idDetalleCompra = 0;

    public static List<Compra> getCompras() {
        List<Compra> compraList = new ArrayList<Compra>(compras.values());
        return compraList;
    }

    public static Response agregarCompra(Compra compra){
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        compra.setDate(dateString);
        compra.setId(++idCompra);
        Float total = new Float(0.0);


        for (CompraDetalle compraDetalle : compra.getCompraDetalles()){
            Producto producto = ProductoServicios.buscarProducto(compraDetalle.getIdProducto());
            if(!(producto.getProveedor().getId().equals(compra.getProveedor().getId()))) {
                return Response.status(500).entity("El producto" + producto.toString() + "no pertenece al proveedor seleccionado: " + compra.getProveedor().toString() + "\nNo se pudo realizar la compra").build() ;
            }
        }

        for (CompraDetalle compraDetalle : compra.getCompraDetalles()){
            compraDetalle.setId(++idDetalleCompra);
            Producto producto = ProductoServicios.buscarProducto(compraDetalle.getIdProducto());
            producto.setCantidad(producto.getCantidad() + compraDetalle.getCantidad());
            ProductoServicios.modificarProducto(producto);
            total = total + ((producto.getPrecioUnitario() * compraDetalle.getCantidad()));
        }

        compra.setTotal(total);
        compras.put(idCompra, compra);
        return Response.status(200).entity(compra.toString()).build();
    }

}
