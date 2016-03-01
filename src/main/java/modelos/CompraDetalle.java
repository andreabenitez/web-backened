package modelos;

import servicios.ProductoServicios;

import java.io.Serializable;

/**
 * Created by andrea on 29/02/16.
 */
public class CompraDetalle implements Serializable {
    private Integer id;
    private Integer idProducto;
    private Integer cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CompraDetalle{" +
                "id=" + id +
                ", producto=" + ProductoServicios.buscarProducto(idProducto).toString() +
                ", cantidad=" + cantidad +
                '}';
    }
}
