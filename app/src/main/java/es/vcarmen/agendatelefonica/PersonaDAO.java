package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.os.Parcelable;
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
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;

    public void removePersona(Object persona){
        listaPersonas.remove(persona);
        guardarListaPersonasEnFirebase(listaPersonas);
    }

    public ArrayList<Object> mostrarPersonas(){
        return this.listaPersonas;
    }

    public void actualizarPersonas(ArrayList<Object> listaActualizada) { this.listaPersonas = listaActualizada; }

    public void addPersonaFireBase(Persona persona, final PersonaDAO dao, final ActivityPrincipal activity){
        Log.v("FirebaseEmail", "PersonaDAO:addEmpresaFireBase:Longitud lista:" + listaPersonas.size());
        String posicionEnLaBd = listaPersonas.size() + "";

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaPersonas").child(posicionEnLaBd).setValue(persona);
        //myRef.setValue("Hello, World!");

        myRef.child("listaPersonas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseEmail", "PersonaDAO:addPersonaFirebase:DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaPersonas = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseEmail", "PersonaDAO:addPersonaFirebase:onDataChange:Contenido lista en dao:"+mostrarPersonas());
                activity.reemplazarFragmentoPrincipal(new Fragmento1(dao));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void guardarListaPersonasEnFirebase(ArrayList<Object> listaPersonas){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaPersonas").setValue(listaPersonas);
        //myRef.setValue("Hello, World!");

        Log.v("FirebaseEmail", "PersonaDAO:listaLongitud:" + listaPersonas.size() + ":usuario:" + usuario.getEmail());
    }

}
