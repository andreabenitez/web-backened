package modelos;

import servicios.ProductoServicios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by andrea on 29/02/16.
 */
@Entity
@Table(name = "compradetalle")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CompraDetalle.findAll", query = "SELECT cd FROM CompraDetalle cd"),
        @NamedQuery(name = "CompraDetalle.findById", query = "SELECT cd FROM CompraDetalle cd WHERE cd.idCompraDetalle = :id")
})
public class CompraDetalle implements Serializable {

    private static final long serialVersionUID = -6010385959003266635L;

    @Id
    @Column(name = "id_compra_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompraDetalle;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    private Compra compra;

    public Integer getIdCompraDetalle() {
        return idCompraDetalle;
    }

    public void setIdCompraDetalle(Integer id) {
        this.idCompraDetalle = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
