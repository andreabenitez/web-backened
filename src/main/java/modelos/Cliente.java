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
@XmlRootElement
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String apellidos;
    private String cedula;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String apellidos, String nombre, String cedula,  String email) {
        this.id = id;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cedula='" + cedula + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
