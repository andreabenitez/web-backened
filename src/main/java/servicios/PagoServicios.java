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

	private static int idPago =0;
    private static HashMap<Integer, Pago> pagos = new HashMap<Integer, Pago>();
    
    public static List<Pago> getPagos() {
        List<Pago> pagoList = new ArrayList<Pago>(pagos.values());
        return pagoList;
    }

    public static Response agregarPago(Pago pago){
    	 pago.setId(++idPago);
         Date date = new Date();
         String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
         pago.setFecha(dateString);
         Float total = new Float(0.0);
         
         Venta venta = pago.getVenta();
         total= venta.getTotal();
         venta.setTotal(venta.getTotal() - total);
         pago.setCliente(venta.getCliente());
         pago.setMonto(total);
         pagos.put(idPago, pago);
         return Response.status(200).entity(pago.toString()+ "Pago exitoso!! ").build();
     
         
    	
    }
}
