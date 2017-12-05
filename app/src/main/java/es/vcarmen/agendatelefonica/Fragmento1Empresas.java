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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1Empresas extends Fragment {

    @BindView(R.id.listaContactosFragmento) ListView lvListaEmpresas;
    @BindView(R.id.botonNuevoContacto)
    FloatingActionButton boton;
    private Activity activity;
    private EmpresaDAO empresaDAO;
    private ArrayList<Object> listaEmpresas = new ArrayList<>();
    private AlertDialog.Builder dialogo;
    private View view;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public Fragmento1Empresas(){}

    public Fragmento1Empresas(EmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO;
        this.listaEmpresas = empresaDAO.mostrarEmpresas();
        Log.v("Fragmento1Empresas", "F1Empresas:Constructor:lista empresas:" + listaEmpresas);
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
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2Empresas(empresaDAO));
    }

    @OnItemClick(R.id.listaContactosFragmento)
    public void accionLista(int posicion){
        //listaEmpresas = empresaDAO.mostrarEmpresas();
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3Empresas(listaEmpresas, posicion));
    }

    @OnItemLongClick(R.id.listaContactosFragmento)
    public boolean accionBorrarContacto(int posicion){
        //listaEmpresas = empresaDAO.mostrarEmpresas();
        final int numeroEnLista = posicion;
        dialogo = new AlertDialog.Builder(view.getContext());
        //dialogo.setView(R.layout.alert_dialog);
        dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + /*listaEmpresas.get(posicion).get("") + */"'?");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                empresaDAO.removeEmpresa(listaEmpresas.get(numeroEnLista));
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1Empresas(empresaDAO));
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
        empresaDAO.actualizarEmpresas(listaEmpresas);
        Log.v("Fragmento1Empresas", "F1Empresas:initialize():"+ listaEmpresas);
        //Log.v("FirebaseEmail", "F1:initialize():listaEnDAO:"+empresaDAO.mostrarEmpresas());
        if(!listaEmpresas.isEmpty()){
            lvListaEmpresas.setVisibility(View.VISIBLE);
        }

        lvListaEmpresas.setAdapter(new EmpresaAdapter(getActivity().getApplicationContext(), listaEmpresas));
    }

    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();
        Log.v("Fragmento1Empresas", "Has entrado en accionesFirebase():" + usuario.getEmail());

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    //usuario ya logueado
                    Log.v("Fragmento1Empresas", "onAuthStateChanged:usuario_logueado:Email:" + usuario.getEmail());
                }else{
                    //usuario no logueado
                    Log.v("Fragmento1Empresas", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
    }

}
