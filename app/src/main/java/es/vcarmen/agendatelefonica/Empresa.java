package es.vcarmen.agendatelefonica;

import java.io.Serializable;

/**
 * Created by matinal on 03/10/2017.
 */

public class Empresa implements Serializable{
    private String nombreEmpresa;
    private String direccionEmpresa;
    private String telefonoCorporativo;
    private String localidadEmpresa;
    private String emailCorporativo;
    private String contactoAsociado;
    private String provinciaEmpresa;
    private String observaciones;
    private int foto;

    //nombreEmpresa, direccionEmpresa, telefonoCorporativo, localidadEmpresa, emailCorporativo, contactoAsociado, provinciaEmpresa, localidadEmpresa, foto
    public Empresa(String nombre, String direccion, String telefono, String localidad, String email, String contacto, String provincia, String observaciones, int imagen) {
        this.nombreEmpresa = nombre;
        this.direccionEmpresa = direccion;
        this.telefonoCorporativo = telefono;
        this.localidadEmpresa = localidad;
        this.emailCorporativo = email;
        this.contactoAsociado = contacto;
        this.provinciaEmpresa = provincia;
        this.observaciones = observaciones;
        this.foto = imagen;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public String getTelefonoCorporativo() {
        return telefonoCorporativo;
    }

    public String getLocalidadEmpresa() {
        return localidadEmpresa;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public String getContactoAsociado() {
        return contactoAsociado;
    }

    public String getProvinciaEmpresa() {
        return provinciaEmpresa;
    }

    public int getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombreEmpresa='" + nombreEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                ", telefonoCorporativo='" + telefonoCorporativo + '\'' +
                ", localidadEmpresa='" + localidadEmpresa + '\'' +
                ", emailCorporativo='" + emailCorporativo + '\'' +
                ", contactoAsociado='" + contactoAsociado + '\'' +
                ", provinciaEmpresa='" + provinciaEmpresa + '\'' +
                ", foto=" + foto +
                '}';
    }
}
