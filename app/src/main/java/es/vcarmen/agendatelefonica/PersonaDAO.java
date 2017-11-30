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
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;

    public void addPersona(Object persona){
        listaPersonas.add(persona);
    }

    public void removePersona(Object persona){
        listaPersonas.remove(persona);
    }

    public ArrayList<Object> mostrarPersonas(){
        return this.listaPersonas;
    }

    public void actualizarPersonas(ArrayList<Object> listaActualizada) { this.listaPersonas = listaActualizada; }

    public void guardarListaPersonasEnFirebase(ArrayList<Object> listaPersonas){
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
