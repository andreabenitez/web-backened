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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
        @NamedQuery(name = "Venta.findById", query = "SELECT v FROM Venta v WHERE v.idVenta = :id")
})
public class Venta implements Serializable {

    private static final long serialVersionUID = 5956326146728513395L;

    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "total")
    private Float total;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER)
    List<VentaDetalle> ventaDetalles;

    @NotNull
    @Column(name = "saldo_deuda")
    private Float saldoDeuda;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<VentaDetalle> getVentaDetalles() {
        return ventaDetalles;
    }

    public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
        this.ventaDetalles = ventaDetalles;
    }

    public Float getSaldoDeuda() {
        return saldoDeuda;
    }

    public void setSaldoDeuda(Float saldoDeuda) {
        this.saldoDeuda = saldoDeuda;
    }


}
