/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 * Created by andrea on 27/02/16.
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
        @NamedQuery(name = "Proveedor.findById", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor")
})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 7032183068501280419L;

    @Id
    @Column(name = "id_proveedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "telefono")
    private String telefono;

    @NotNull
    @Column(name = "direccion")
    private String direccion;


    public Proveedor() {
    }

    public Proveedor(Integer idProveedor, String nombre, String telefono, String direccion){
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, String nombre) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer id) {
        this.idProveedor = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
