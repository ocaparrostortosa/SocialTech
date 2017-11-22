package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
import butterknife.OnItemLongClick;

/**
 * Created by OSCAR on 14/11/2017.
 */

public class Fragmento1Empresa extends Fragment {

    @BindView(R.id.listaEmpresasFragmento) ListView lvListaContactos;
    @BindView(R.id.botonNuevaEmpresa) Button boton;
    private Activity activity;
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private ArrayList<Empresa> listaEmpresas;
    private AlertDialog.Builder dialogo;
    private View view;

    public Fragmento1Empresa(){}

    public Fragmento1Empresa(EmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragmento1empresa, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //initialize();
    }

    @OnClick(R.id.botonNuevaEmpresa)
    public void accionBoton(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2Empresa(empresaDAO));
    }
/**
    @OnItemClick(R.id.listaContactosFragmento)
    public void accionLista(int posicion){
        listaEmpresas = empresaDAO.mostrarPersonas();
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento3(listaEmpresas.get(posicion)));
    }

    @OnItemLongClick(R.id.listaContactosFragmento)
    public boolean accionBorrarContacto(int posicion){
        listaEmpresas = empresaDAO.mostrarPersonas();
        final int numeroEnLista = posicion;
        dialogo = new AlertDialog.Builder(view.getContext());
        dialogo.setTitle("¿Seguro que quiere eliminar el contacto '" + listaEmpresas.get(posicion).getNombre() + "'?");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                empresaDAO.removePersona(listaEmpresas.get(numeroEnLista));
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1Empresa(empresaDAO));
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
        lvListaContactos.setAdapter(new PersonaAdapter(getActivity().getApplicationContext(), (ArrayList<Persona>) empresaDAO.mostrarPersonas()));
    }

*/
}
