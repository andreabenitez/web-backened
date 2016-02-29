/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by andrea on 27/02/16.
 */
@XmlRootElement
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private Integer cantidad;
    private Float precioUnitario;
    private String descripcion;
    private Proveedor proveedor;



    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre, Integer cantidad, Float precioUnitario, String descripcion, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public Producto(Integer id, String nombre, Float precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", descripcion='" + descripcion + '\'' +
                ", proveedor=" + proveedor.toString() +
                '}';
    }
}
