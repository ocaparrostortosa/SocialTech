package es.vcarmen.agendatelefonica;

import java.io.Serializable;

/**
 * Created by OSCAR on 14/11/2017.
 */

public class Empresa implements Serializable{
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String telefonoCorporativo;
    private String emailCorporativo;
    private String Observaciones;
    private Persona contactoAsociado;

    public Empresa() {
    }

    public Empresa(String nombre, String direccion, String localidad, String provincia, String telefonoCorporativo, String emailCorporativo, String observaciones, Persona contactoAsociado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefonoCorporativo = telefonoCorporativo;
        this.emailCorporativo = emailCorporativo;
        Observaciones = observaciones;
        this.contactoAsociado = contactoAsociado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefonoCorporativo() {
        return telefonoCorporativo;
    }

    public void setTelefonoCorporativo(String telefonoCorporativo) {
        this.telefonoCorporativo = telefonoCorporativo;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public Persona getContactoAsociado() {
        return contactoAsociado;
    }

    public void setContactoAsociado(Persona contactoAsociado) {
        this.contactoAsociado = contactoAsociado;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", telefonoCorporativo='" + telefonoCorporativo + '\'' +
                ", emailCorporativo='" + emailCorporativo + '\'' +
                ", Observaciones='" + Observaciones + '\'' +
                ", contactoAsociado=" + contactoAsociado +
                '}';
    }
}
