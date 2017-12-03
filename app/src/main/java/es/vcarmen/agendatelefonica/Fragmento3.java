package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento3 extends Fragment {

    private ArrayList<Object> listaPersonas;
    private View vista;
    private int posicion;

    public Fragmento3(){}

    public Fragmento3(ArrayList<Object> listaPersonas, int posicion){
        this.listaPersonas = listaPersonas;
        this.posicion = posicion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.layout_fragmento3, container, false);
        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        HashMap<String, String> objeto = (HashMap<String, String>) listaPersonas.get(posicion);
        Log.v("FirebaseEmail","F3:intialize():Contenido lista:" + listaPersonas);
        Log.v("FirebaseEmail","F3:intialize():Contenido objeto:" + objeto.get("nombre").toString());

        TextView nombre = (TextView) getView().findViewById(R.id.tvName);
        TextView apellidos = (TextView) getView().findViewById(R.id.tvSurname);
        TextView email = (TextView) getView().findViewById(R.id.tvEmail);
        TextView sexo = (TextView) getView().findViewById(R.id.tvSexo);
        TextView telefono = (TextView) getView().findViewById(R.id.tvTelefono);
        TextView estudios = (TextView) getView().findViewById(R.id.tvEstudios);
        TextView provincia = (TextView) getView().findViewById(R.id.tvProvincia);
        TextView edad = (TextView) getView().findViewById(R.id.tvEdad);
        ImageView imagen = getView().findViewById(R.id.imagenContacto);

        nombre.append(objeto.get("nombre").toString());
        apellidos.append(objeto.get("apellidos").toString());
        email.append(objeto.get("email").toString());
        sexo.append(objeto.get("sexo").toString());
        telefono.append(objeto.get("telefono").toString());
        estudios.append(objeto.get("estudios").toString());
        provincia.append(objeto.get("provincia").toString());
        Log.v("FirebaseEmail","F3:intialize():Tipo objeto edad:");
        edad.append(String.valueOf(objeto.get("edad")));
        imagen.setImageDrawable(getView().getResources().getDrawable(Integer.parseInt(String.valueOf(objeto.get("imagen"))), getActivity().getApplicationContext().getTheme()));

        accionImagenContacto(imagen, objeto.get("nombre").toString());
    }

    private void accionImagenContacto(ImageView imagen, final String telefono){
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionSnackBarImagenContacto(telefono);
            }
        });
    }

    private void accionSnackBarImagenContacto(final String telefono){
        Snackbar snackbar = Snackbar.make(vista, "¿LLAMAR A: " + telefono, Snackbar.LENGTH_LONG);
        snackbar.setAction("ACEPTAR", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + telefono));
                startActivity(callIntent);
            }
        });
        snackbar.show();
    }
}
