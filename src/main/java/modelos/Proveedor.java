/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 * Created by andrea on 27/02/16.
 */
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String telefono;
    private String direccion;


    public Proveedor() {
    }

    public Proveedor(Integer id) {
        this.id = id;
    }

    public Proveedor(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
