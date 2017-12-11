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

    /**
     *
     * @param nombre
     * @param direccion
     * @param telefono
     * @param localidad
     * @param email
     * @param contacto
     * @param provincia
     * @param observaciones
     * @param imagen
     */
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
                ", observaciones='" + observaciones + '\'' +
                ", foto=" + foto +
                '}';
    }

    /**
     *
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

}
