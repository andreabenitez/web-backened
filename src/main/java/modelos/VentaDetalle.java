package modelos;

import org.hibernate.annotations.Check;
import servicios.ProductoServicios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by andrea on 29/02/16.
 */
@Entity
@Table(name = "ventadetalle")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VentaDetalle.findAll", query = "SELECT vd FROM VentaDetalle vd"),
        @NamedQuery(name = "VentaDetalle.findById", query = "SELECT vd FROM VentaDetalle vd WHERE vd.idVentaDetalle = :id")
})
@Check(constraints = "cantidad > 0")
public class VentaDetalle implements Serializable{

    private static final long serialVersionUID = -8767407091704984773L;

    @Id
    @Column(name = "id_venta_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVentaDetalle;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    private Venta venta;

    public Integer getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}
