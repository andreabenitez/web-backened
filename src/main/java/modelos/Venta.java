package modelos;

import java.util.List;

/**
 * Created by andrea on 29/02/16.
 */
public class Venta {

    private Integer id;
    private String date;
    private Float total;
    private Cliente cliente;
    List<VentaDetalle> ventaDetalles;
    private Float saldoDeuda;

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

    @Override
    public String toString() {
        String result = "Venta{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", cliente=" + cliente.toString() +
                ", ventaDetalles= {";

        for (VentaDetalle ventaDetalle : ventaDetalles){
            result = result + ventaDetalle.toString();
        }
        result = result +   '}';
        return result;
    }
}
