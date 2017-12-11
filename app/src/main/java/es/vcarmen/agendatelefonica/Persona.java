package es.vcarmen.agendatelefonica;

import java.io.Serializable;

/**
 * Created by matinal on 03/10/2017.
 */
public class Persona implements Serializable{
    private String nombre;
    private String apellidos;
    private String telefono;
    private String sexo;
    private String email;
    private String estudios;
    private String provincia;
    private int edad;
    private int imagen;

    /**
     *
     * @param nombre
     * @param apellidos
     * @param telefono
     * @param sexo
     * @param email
     * @param estudios
     * @param provincia
     * @param edad
     * @param imagen
     */
    public Persona(String nombre, String apellidos, String telefono, String sexo, String email, String estudios, String provincia, int edad, int imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sexo = sexo;
        this.email = email;
        this.estudios = estudios;
        this.provincia = provincia;
        this.edad = edad;
        this.imagen = imagen;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return nombre+":"+apellidos+":"+telefono+":"+sexo+":"+email+":"+estudios+":"+provincia+":"+edad;
    }
}
