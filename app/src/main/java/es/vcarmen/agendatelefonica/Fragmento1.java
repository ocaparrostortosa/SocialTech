package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1 extends Fragment {

    private Activity activity;
    private Button boton;
    private List<Persona> listaPersonas = new ArrayList<Persona>();
    private ListView lvListaContactos;

    public Fragmento1(){}

    public Fragmento1(List<Persona> listaPersonas){
        this.listaPersonas = listaPersonas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmento1, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        accionListaContactos();

        boton = (Button)getView().findViewById(R.id.botonNuevoContacto);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2());
            }
        });
    }

    private void accionListaContactos(){
        lvListaContactos = getView().findViewById(R.id.listaContactosFragmento);
        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), (ArrayList<Persona>) listaPersonas));

        lvListaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(listaPersonas.get(i)));
            }
        });
    }
}
