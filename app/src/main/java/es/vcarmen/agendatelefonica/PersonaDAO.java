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
<<<<<<< HEAD
=======

>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    //Valor estático
    private static final String listaContactos = "listaPersonas";

    public void addPersona(Object persona){
        listaPersonas.add(persona);
    }

    public void removePersona(Object persona){
        listaPersonas.remove(persona);
    }

    public ArrayList<Object> mostrarPersonas(){
<<<<<<< HEAD
        return this.listaPersonas;
    }

    public void actualizarPersonas(ArrayList<Object> listaActualizada) { this.listaPersonas = listaActualizada; }

    public void guardarListaPersonasEnFirebase(ArrayList<Object> listaPersonas){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
=======
        return listaPersonas;
    }

    public void guardarListaContactosEnFirebase(ArrayList<Object> listaPersonas2){
        this.listaPersonas = listaPersonas2;
        inicializarVariablesFirebase();
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94

        // Escribir datos en Firebase
        //Log.v("LISTACONTACTOS", ":" + listaPersonas.toString());
        if(!listaPersonas.isEmpty()) {
            Log.v("FirebaseEmail", ":guardarListaFirebase:ConDatos:" + listaPersonas);
            myRef.child(listaContactos).setValue(this.listaPersonas);
        }else
            Log.v("FirebaseEmail", ":guardarListaFirebase:Vacia");

    }

    public void guardarListaContactosEnFirebase(){
        inicializarVariablesFirebase();

        // Escribir datos en Firebase
        //Log.v("LISTACONTACTOS", ":" + listaPersonas.toString());
        if(!listaPersonas.isEmpty()) {
            Log.v("FirebaseEmail", ":guardarListaFirebase:ConDatos:" + listaPersonas);
            myRef.child(listaContactos).setValue(listaPersonas);
        }else
            Log.v("FirebaseEmail", ":guardarListaFirebase:Vacia");

    }

    public ArrayList obtenerListaContactosDeFirebase(){
        inicializarVariablesFirebase();

        //Leer datos en Firebase
        myRef.child(listaContactos).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaPersonas = (ArrayList) dataSnapshot.getValue();
                Log.d("FirebaseEmail", "PersonaDAO:longitud:" + listaPersonas.size() + ":listaLeida:" + listaPersonas);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FirebaseEmail", "PersonaDAO:listaLeida:Error al leer en firebase.");
            }
        });

        return listaPersonas;
    }

    private void inicializarVariablesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(""+usuario.getUid());
        Log.v("FirebaseEmail", ":Usuario logueado:" + usuario.getUid());
    }

}
