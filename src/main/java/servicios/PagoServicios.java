package servicios;

import modelos.Pago;
import modelos.Venta;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PagoServicios {

    @Inject
    private VentaServicios ventaServicios;

    @PersistenceContext(unitName = "persistenciaApp")
    private EntityManager entityManager;

    public List<Pago> getPagos() {
        return entityManager.createNamedQuery("Pago.findAll").getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response agregarPago(Pago pago) {
        try {
            Venta venta = ventaServicios.buscarVentaPorId(pago.getVenta().getIdVenta());
            if (venta.getSaldoDeuda() > 0) {
                String mensajeRespuesta;

                String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                pago.setFecha(dateString);
                if (venta.getSaldoDeuda() > pago.getMontoPagado()) {
                    venta.setSaldoDeuda(venta.getSaldoDeuda() - pago.getMontoPagado());
                    mensajeRespuesta = "El cliente aun debe abonar " + (venta.getTotal() - pago.getMontoPagado()) + "para pagar completar el pago de la venta: \n" + venta.toString();
                    entityManager.merge(venta);
                } else {
                    if (pago.getMontoPagado() > venta.getSaldoDeuda()) {
                        pago.setMontoPagado(venta.getSaldoDeuda());
                    }
                    mensajeRespuesta = "La deuda por la Venta:" + venta.toString() + "ha sido totalmente pagada";
                    venta.setSaldoDeuda((float) 0);
                    entityManager.merge(venta);
                }

                entityManager.persist(pago);

                return Response.status(200).entity(pago.toString() + mensajeRespuesta).build();
            } else {
                return Response.status(200).entity(pago.toString() + "El pago de esta venta ya se pagado anteriormente" + pago.toString()).build();
            }
        }catch(Exception e){
            return Response.status(500).entity("Ha ocurrido un error en el pago: " + e.getMessage()).build();
        }
    }
}
