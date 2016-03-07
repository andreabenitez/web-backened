package modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
        @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.idCompra = :id")
})
public class Compra implements Serializable {

    private static final long serialVersionUID = 9155438880170889159L;

    @Id
    @Column(name = "id_compra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "total")
    private Float total;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "compra", fetch = FetchType.EAGER)
    private List<CompraDetalle> compraDetalles;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer id) {
        this.idCompra = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<CompraDetalle> getCompraDetalles() {
        return compraDetalles;
    }

    public void setCompraDetalles(List<CompraDetalle> compraDetalles) {
        this.compraDetalles = compraDetalles;
    }

}
