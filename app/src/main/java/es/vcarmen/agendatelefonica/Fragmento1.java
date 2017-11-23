package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    private PersonaDAO personaDAO = new PersonaDAO();
    private ArrayList<Persona> listaPersonas;
    private AlertDialog.Builder dialogo;
    private View view;

    public Fragmento1(){}

    public Fragmento1(PersonaDAO personaDAO){
        this.personaDAO = personaDAO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragmento1, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    @OnClick(R.id.botonNuevoContacto)
    public void accionBoton(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2(personaDAO));
    }

    @OnItemClick(R.id.listaContactosFragmento)
    public void accionLista(int posicion){
        listaPersonas = personaDAO.mostrarPersonas();
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(listaPersonas.get(posicion)));
    }

    @OnItemLongClick(R.id.listaContactosFragmento)
    public boolean accionBorrarContacto(int posicion){
        listaPersonas = personaDAO.mostrarPersonas();
        final int numeroEnLista = posicion;
        dialogo = new AlertDialog.Builder(view.getContext());
        //dialogo.setView(R.layout.alert_dialog);
        dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + listaPersonas.get(posicion).getNombre() + "'?");
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
        listaPersonas = personaDAO.mostrarPersonas();
        if(!listaPersonas.isEmpty()){
            lvListaContactos.setVisibility(View.VISIBLE);
        }

        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), (ArrayList<Persona>) personaDAO.mostrarPersonas()));
    }


}
