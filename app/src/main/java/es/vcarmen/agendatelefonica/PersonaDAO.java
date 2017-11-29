package es.vcarmen.agendatelefonica;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matinal on 03/10/2017.
 */

public class PersonaDAO implements Serializable {
    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;

    public void addPersona(Persona persona){
        listaPersonas.add(persona);
    }

    public void removePersona(Persona persona){
        listaPersonas.remove(persona);
    }

    public ArrayList<Persona> mostrarPersonas(){
        return listaPersonas;
    }

    public void guardarListaPersonasEnFirebase(){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaPersonas").setValue(listaPersonas);
        //myRef.setValue("Hello, World!");

        Log.d("FirebaseEmail", "PersonaDAO:listaLongitud:" + listaPersonas.size() + ":usuario:" + usuario.getEmail());
    }

}
