package es.vcarmen.agendatelefonica;

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

    private Button boton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmento1, container, false);
        boton = (Button) view.findViewById(R.id.botonNuevoContacto);
        ActivityPrincipal.accionBotonNuevoContacto();
        return view;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        boton.setOnClickListener(onClickListener);
    }
}
