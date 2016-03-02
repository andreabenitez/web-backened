package modelos;

import java.io.Serializable;

public class Pago implements Serializable {

	private Integer id;
	private Integer ventaId;
	private Float montoPagado;
	private String fecha;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getVentaId() {
		return ventaId;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Float getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Float montoPagado) {
		this.montoPagado = montoPagado;
	}

}
