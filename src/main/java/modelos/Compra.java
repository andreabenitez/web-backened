package modelos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class Compra implements Serializable {
    private Integer id;
    private String date;
    private Float total;
    private Proveedor proveedor;
    private List<CompraDetalle> compraDetalles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        String result =  "Compra{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", proveedor=" + proveedor.toString() +
                ", compraDetalles={" ;
        for (CompraDetalle compraDetalle : compraDetalles){
            result = result + compraDetalle.toString();
        }
        result = result + '}';
        return  result;
    }
}
