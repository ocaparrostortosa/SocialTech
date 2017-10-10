package es.vcarmen.agendatelefonica;

/**
 * Created by matinal on 03/10/2017.
 */

public class Persona {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String sexo;

    public Persona(String nombre, String apellidos, String telefono, String sexo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sexo = sexo;
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

    @Override
    public String toString() {
        return nombre+":"+apellidos+":"+telefono+":"+sexo;
    }
}
