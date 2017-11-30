package es.vcarmen.agendatelefonica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matinal on 03/10/2017.
 */

public class Persona extends HashMap<String, ArrayList<Persona>> implements Serializable{
    private String nombre;
    private String apellidos;
    private String telefono;
    private String sexo;
    private String email;
    private String estudios;
    private String provincia;
    private int edad;
    private int imagen;

    public Persona(){}

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

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getEstudios() {
        return estudios;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getEdad() {
        return edad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre+":"+apellidos+":"+telefono+":"+sexo+":"+email+":"+estudios+":"+provincia+":"+edad;
    }
}
