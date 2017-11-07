package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento3Empresas extends Fragment {

    private Persona personaAMostrar;

    public Fragmento3Empresas(){}

    public Fragmento3Empresas(Persona persona){
        this.personaAMostrar = persona;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragmento3, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        TextView nombre = (TextView) getView().findViewById(R.id.tvName);
        TextView apellidos = (TextView) getView().findViewById(R.id.tvSurname);
        TextView email = (TextView) getView().findViewById(R.id.tvEmail);
        TextView sexo = (TextView) getView().findViewById(R.id.tvSexo);
        TextView telefono = (TextView) getView().findViewById(R.id.tvTelefono);
        TextView estudios = (TextView) getView().findViewById(R.id.tvEstudios);
        TextView provincia = (TextView) getView().findViewById(R.id.tvProvincia);
        TextView edad = (TextView) getView().findViewById(R.id.tvEdad);

        nombre.append(personaAMostrar.getNombre()+"");
        apellidos.append(personaAMostrar.getApellidos()+"");
        email.append(personaAMostrar.getEmail()+"");
        sexo.append(personaAMostrar.getSexo()+"");
        telefono.append(personaAMostrar.getTelefono()+"");
        estudios.append(personaAMostrar.getEstudios()+"");
        provincia.append(personaAMostrar.getProvincia()+"");
        edad.append(personaAMostrar.getEdad()+"");
    }
}
