package servicios;




import modelos.Compra;
import modelos.CompraDetalle;
import modelos.Producto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    @Inject
    private ProductoServicios productoServicios;

    public  List<Compra> getCompras() {
        return entityManager.createNamedQuery("Cliente.findAll").getResultList();
    }


    public  Response agregarCompra(Compra compra){

        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        compra.setDate(dateString);
        Float total = new Float(0.0);


        for (CompraDetalle compraDetalle : compra.getCompraDetalles()){
            Producto producto = productoServicios.buscarProducto(compraDetalle.getProducto().getIdProducto());
            if(!(producto.getProveedor().getIdProveedor().equals(compra.getProveedor().getIdProveedor()))) {
                return Response.status(500).entity("El producto" + producto.toString() + "no pertenece al proveedor seleccionado: " + compra.getProveedor().toString() + "\nNo se pudo realizar la compra").build() ;
            }
        }

        for (CompraDetalle compraDetalle : compra.getCompraDetalles()){

            Producto producto = productoServicios.buscarProducto(compraDetalle.getProducto().getIdProducto());
            producto.setCantidad(producto.getCantidad() + compraDetalle.getCantidad());
            productoServicios.modificarProducto(producto);
            total = total + ((producto.getPrecioUnitario() * compraDetalle.getCantidad()));
        }

        compra.setTotal(total);
        entityManager.persist(compra);
        return Response.status(200).entity(compra.toString()).build();
    }


}
