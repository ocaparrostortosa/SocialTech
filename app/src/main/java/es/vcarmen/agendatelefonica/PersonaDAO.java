package es.vcarmen.agendatelefonica;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matinal on 03/10/2017.
 */

public class PersonaDAO implements Serializable {

    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
    private ArrayList<Persona> listaPersonasFirebase = new ArrayList<Persona>();
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    //Valor estático
    private static final String listaContactos = "listaContactos";

    public void addPersona(Persona persona){
        listaPersonas.add(persona);
    }

    public void removePersona(Persona persona){
        listaPersonas.remove(persona);
    }

    public ArrayList<Persona> mostrarPersonas(){
        return listaPersonas;
    }

    public void guardarListaContactosEnFirebase(){
        inicializarVariablesFirebase();

        // Escribir datos en Firebase
        myRef.child(listaContactos).setValue(listaPersonas);

        //Log.d("FirebaseEmail", "PersonaDAO:listaLongitud:" + listaPersonas.size() + ":usuario:" + usuario.getEmail());
    }

    public ArrayList obtenerListaContactosDeFirebase(){
        inicializarVariablesFirebase();

        //Leer datos en Firebase
        myRef.child(listaContactos).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaPersonasFirebase = (ArrayList) dataSnapshot.getValue();
                Log.d("FirebaseEmail", "PersonaDAO:longitud:" + listaPersonas.size() + ":listaLeida:" + listaPersonas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FirebaseEmail", "PersonaDAO:listaLeida:Error al leer en firebase.");
            }
        });
        Log.d("FirebaseEmail", "PersonaDAO:obteneeeer:longitud:" + listaPersonas.size() + ":listaLeida:" + listaPersonas);
        while (listaPersonas.size() == 0)
            listaPersonas = listaPersonasFirebase;
        return listaPersonas;
    }

    private void inicializarVariablesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(""+usuario.getUid());
    }

}
