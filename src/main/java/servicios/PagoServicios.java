package servicios;

import modelos.Pago;
import modelos.Venta;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;


public class PagoServicios {

    private static int idPago = 0;
    private static HashMap<Integer, Pago> pagos = new HashMap<Integer, Pago>();

    public static List<Pago> getPagos() {
        List<Pago> pagoList = new ArrayList<Pago>(pagos.values());
        return pagoList;
    }

    public static Response agregarPago(Pago pago) {
        Venta venta = VentaServicios.buscarVentaPorId(pago.getVentaId());
        if (venta.getSaldoDeuda() > 0) {
            String mensajeRespuesta;
            pago.setId(++idPago);
            Date date = new Date();

            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            pago.setFecha(dateString);
            if (venta.getSaldoDeuda() > pago.getMontoPagado()) {
                venta.setSaldoDeuda(venta.getSaldoDeuda() - pago.getMontoPagado());
                mensajeRespuesta = "El cliente aun debe abonar " + (venta.getTotal() - pago.getMontoPagado()) + "para pagar completar el pago de la venta: \n" + venta.toString();
                VentaServicios.modificarVenta(venta);
            } else {
                mensajeRespuesta = "La deuda por la Venta:" + venta.toString() + "ha sido totalmente pagada";
                venta.setSaldoDeuda((float) 0);
                VentaServicios.modificarVenta(venta);
            }

            pagos.put(idPago, pago);

            return Response.status(200).entity(pago.toString() + mensajeRespuesta).build();
        }
        else{
            return Response.status(200).entity(pago.toString() + "El pago de esta venta ya se pagado anteriormente" + pago.toString()).build();
        }
    }
}
