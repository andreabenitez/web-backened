package servicios;

import modelos.*;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class VentaServicios {

    private static HashMap<Integer, Venta> ventas = new HashMap<>();

    private static int idVenta =0;
    private static int idDetalleVenta = 0;

    public static List<Venta> getVentas() {
        List<Venta> ventaList = new ArrayList<Venta>(ventas.values());
        return ventaList;
    }

    public static Response agregarVenta(Venta venta){
        venta.setId(++idVenta);
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        venta.setDate(dateString);
        Float total = new Float(0.0);
        List<Producto> productoModificadoList = new ArrayList<>();

        for(VentaDetalle ventaDetalle : venta.getVentaDetalles()){
            Producto producto = ProductoServicios.buscarProducto(ventaDetalle.getIdProducto());
            if(!(producto.getCantidad() >= ventaDetalle.getCantidad())) {
                return Response.status(500).entity("Insuficiencia en stock del producto :" + producto.toString()).build();
            }
        }

        for(VentaDetalle ventaDetalle : venta.getVentaDetalles()){
            ventaDetalle.setId(++idDetalleVenta);
            Producto producto = ProductoServicios.buscarProducto(ventaDetalle.getIdProducto());
            producto.setCantidad(producto.getCantidad() - ventaDetalle.getCantidad());
            ProductoServicios.modificarProducto(producto);
            total = total + (producto.getPrecioUnitario() * ventaDetalle.getCantidad())* new Float(1.1);
        }


        venta.setTotal(total);
        ventas.put(idVenta, venta);
        return Response.status(200).entity(venta.toString()).build();
    }
}
