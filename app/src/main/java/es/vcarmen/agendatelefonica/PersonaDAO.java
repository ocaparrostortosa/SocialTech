package es.vcarmen.agendatelefonica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matinal on 03/10/2017.
 */

public class PersonaDAO implements Serializable {
    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

    public void addPersona(Persona persona){
        listaPersonas.add(persona);
    }

    public void removePersona(Persona persona){
        listaPersonas.remove(persona);
    }

    public ArrayList<Persona> mostrarPersonas(){
        return listaPersonas;
    }

}
