package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
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
    private PersonaDAO personaDAO;
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
    private AlertDialog.Builder dialogo;
    private View view;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     *
     */
    public Fragmento1(){}

    /**
     *
     * @param personaDAO
     */
    public Fragmento1(PersonaDAO personaDAO){
        this.personaDAO = personaDAO;
        this.listaPersonas = personaDAO.mostrarPersonas();
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

    /**
     *
     */
    @OnClick(R.id.botonNuevoContacto)
    public void accionBoton(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2(personaDAO));
    }

    /**
     *
     * @param posicion
     */
    @OnItemClick(R.id.listaContactosFragmento)
    public void accionLista(int posicion){
        //listaPersonas = personaDAO.mostrarEmpresas();
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(listaPersonas, posicion));
    }

    /**
     *
     * @param posicion
     * @return
     */
    @OnItemLongClick(R.id.listaContactosFragmento)
    public boolean accionBorrarContacto(int posicion){
        //listaPersonas = personaDAO.mostrarEmpresas();
        final int numeroEnLista = posicion;
        dialogo = new AlertDialog.Builder(view.getContext());
        //dialogo.setView(R.layout.alert_dialog);
        dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + /*listaPersonas.get(posicion).get("") + */"'?");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                personaDAO.removePersona(listaPersonas.get(numeroEnLista));
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1(personaDAO));
            }
        });
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
        ActivityPrincipal activityPrincipal = (ActivityPrincipal) getActivity();
        if(!activityPrincipal.isEstado()) {
            activityPrincipal.setEstado(true);
            Menu menu = activityPrincipal.getMenu();
            activityPrincipal.onCreateOptionsMenu(menu);
            TextView tituloToolbar = (TextView) ((ActivityPrincipal) getActivity()).findViewById(R.id.toolbar_title);
            tituloToolbar.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        }

        personaDAO.actualizarPersonas(listaPersonas);
        Log.v("FirebaseEmail", "F1:initialize():"+listaPersonas);
        //Log.v("FirebaseEmail", "F1:initialize():listaEnDAO:"+personaDAO.mostrarEmpresas());
        if(!listaPersonas.isEmpty()){
            lvListaContactos.setVisibility(View.VISIBLE);
        }

        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), listaPersonas));
    }

    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();
        Log.d("FirebaseEmail", "Has entrado en accionesFirebase():" + usuario.getEmail());

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
