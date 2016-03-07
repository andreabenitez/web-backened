package modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
		@NamedQuery(name = "Pago.findById", query = "SELECT p FROM Pago p WHERE p.idPago = :id")
})
public class Pago implements Serializable {

	private static final long serialVersionUID = 8157232896330549773L;

	@Id
	@Column(name = "id_pago")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPago;

	@ManyToOne
	@JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
	private Venta venta;

	@NotNull
	@Column(name = "monto_pagado")
	private Float montoPagado;

	@NotNull
	@Column(name = "fecha")
	private String fecha;


	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer id) {
		this.idPago = id;
	}


	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
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
