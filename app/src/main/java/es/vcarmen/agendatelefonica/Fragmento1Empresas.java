package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1Empresas extends Fragment {

    @BindView(R.id.listaContactosFragmento) ListView lvListaContactos;
    @BindView(R.id.botonNuevoContacto) Button boton;
    private Activity activity;
    private PersonaDAO personaDAO = new PersonaDAO();
    private ArrayList<Persona> listaPersonas;

    public Fragmento1Empresas(){}

    public Fragmento1Empresas(PersonaDAO personaDAO){
        this.personaDAO = personaDAO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmento1, container, false);
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



    private void initialize(){
        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), (ArrayList<Persona>) personaDAO.mostrarPersonas()));
    }


}
