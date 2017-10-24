package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1 extends Fragment {

    private Activity activity;
    private Button boton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmento1, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        inicialize();
    }

    private void inicialize(){
        boton = (Button)getView().findViewById(R.id.botonNuevoContacto);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento2());
            }
        });
    }
}
