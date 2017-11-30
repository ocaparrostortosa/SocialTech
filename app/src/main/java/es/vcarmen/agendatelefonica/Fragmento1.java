package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1 extends Fragment {

    @BindView(R.id.listaContactosFragmento) ListView lvListaContactos;
    @BindView(R.id.botonNuevoContacto)
    FloatingActionButton boton;
    private Activity activity;
<<<<<<< HEAD
    private PersonaDAO personaDAO;
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
=======
    private PersonaDAO personaDAO = new PersonaDAO();
    private ArrayList<Object> listaPersonas;
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
    private AlertDialog.Builder dialogo;
    private View view;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public Fragmento1(){}

    public Fragmento1(PersonaDAO personaDAO){
        this.personaDAO = personaDAO;
        this.listaPersonas = personaDAO.mostrarPersonas();
    }

    public Fragmento1(ArrayList<Object> listaPersonasFirebase){
        this.listaPersonas = listaPersonasFirebase;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragmento1, container, false);
        accionesFirebase();
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d("Firebase","Usuario logueado actualmente: " + )
        initialize();
    }

    @OnClick(R.id.botonNuevoContacto)
    public void accionBoton(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2(personaDAO));
    }

    @OnItemClick(R.id.listaContactosFragmento)
    public void accionLista(int posicion){
<<<<<<< HEAD
        //listaPersonas = personaDAO.mostrarPersonas();
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(/*listaPersonas.get(posicion)*/));
=======
        /////////////////////((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(listaPersonas.get(posicion)));
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
    }

    @OnItemLongClick(R.id.listaContactosFragmento)
    public boolean accionBorrarContacto(int posicion){
<<<<<<< HEAD
=======
        //listaPersonas = personaDAO.obtenerListaContactosDeFirebase();
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
        //listaPersonas = personaDAO.mostrarPersonas();
        final int numeroEnLista = posicion;
        dialogo = new AlertDialog.Builder(view.getContext());
        //dialogo.setView(R.layout.alert_dialog);
<<<<<<< HEAD
        dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + /*listaPersonas.get(posicion).get("") + */"'?");
=======

        /*dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + listaPersonas.get(posicion).getNombre() + "'?");
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                personaDAO.removePersona(listaPersonas.get(numeroEnLista));
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1(personaDAO));
            }
        });*/
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogo.show();
        return true;
    }


    private void initialize(){
<<<<<<< HEAD
        personaDAO.actualizarPersonas(listaPersonas);
        Log.v("FirebaseEmail", "F1:initialize():"+listaPersonas);
        //Log.v("FirebaseEmail", "F1:initialize():listaEnDAO:"+personaDAO.mostrarPersonas());
        if(!listaPersonas.isEmpty()){
            lvListaContactos.setVisibility(View.VISIBLE);
        }

=======
        //listaPersonas = personaDAO.obtenerListaContactosDeFirebase();
        //listaPersonas = personaDAO.mostrarPersonas();
        if(!listaPersonas.isEmpty()){
            lvListaContactos.setVisibility(View.VISIBLE);
        }
        Log.v("LISTA:", "" + listaPersonas.toString());
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), listaPersonas));
    }

    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();
        Log.d("FirebaseEmail", "Fragmento1:Has entrado en accionesFirebase():" + usuario.getEmail());

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    //usuario ya logueado
                    Log.d("FirebaseEmail", "onAuthStateChanged:usuario_logueado:Email:" + usuario.getEmail());
                }else{
                    //usuario no logueado
                    Log.d("FirebaseEmail", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
    }

}
