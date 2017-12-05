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

/**
 * Created by matinal on 03/10/2017.
 */

public class EmpresaDAO implements Serializable {
    private ArrayList<Object> listaEmpresas = new ArrayList<Object>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;

    public void addEmpresa(Object empresa){
        listaEmpresas.add(empresa);
    }

    public void removeEmpresa(Object empresa){
        listaEmpresas.remove(empresa);
        guardarListaEmpresasEnFirebase(listaEmpresas);
    }

    public ArrayList<Object> mostrarEmpresas(){
        return this.listaEmpresas;
    }

    public void actualizarEmpresas(ArrayList<Object> listaActualizada) { this.listaEmpresas = listaActualizada; }

    public void addEmpresaFireBase(Empresa empresa, final EmpresaDAO dao, final ActivityPrincipal activity){
        Log.v("FirebaseEmail", "EmpresaDAO:addEmpresaFireBase:Longitud lista:" + listaEmpresas.size());
        String posicionEnLaBd = listaEmpresas.size() + "";

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaEmpresas").child(posicionEnLaBd).setValue(empresa);
        //myRef.setValue("Hello, World!");

        myRef.child("listaEmpresas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseEmail", "EmpresaDAO:addEmpresaFirebase:DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaEmpresas = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseEmail", "EmpresaDAO:addEmpresaFirebase:onDataChange:Contenido lista en dao:"+ mostrarEmpresas());
                activity.reemplazarFragmentoPrincipal(new Fragmento1Empresas(dao));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void guardarListaEmpresasEnFirebase(ArrayList<Object> listaEmpresas){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaEmpresas").setValue(listaEmpresas);
        //myRef.setValue("Hello, World!");

        Log.v("FirebaseEmail", "EmpresaDAO:listaLongitud:" + listaEmpresas.size() + ":usuario:" + usuario.getEmail());
    }

}
