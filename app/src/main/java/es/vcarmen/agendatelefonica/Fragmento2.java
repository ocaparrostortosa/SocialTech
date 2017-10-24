package es.vcarmen.agendatelefonica;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento2 extends DialogFragment {

    private Button botonAlta;
    private Button botonBorrar;
    private EditText etNombreContacto;
    private EditText etApellidoContacto;
    private EditText etTelefonoContacto;
    private EditText etSexoContacto;
    private EditText etEmailContacto;
    private MultiAutoCompleteTextView etEstudiosContacto;
    private Spinner etProvinciaContacto;
    private SeekBar etEdadContacto;
    private List<Persona> listaPersonas = new ArrayList<Persona>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragmento2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        inicialize();
    }

    private void inicialize(){
        botonAlta = (Button)getView().findViewById(R.id.botonNuevoContacto);
        botonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionBotonAlta();
                ((ActivityPrincipal)getActivity()).accionBotonAlta();
            }
        });
    }

    private void accionBotonAlta(){
        etNombreContacto = getView().findViewById(R.id.nombreContacto);
        etApellidoContacto = getView().findViewById(R.id.apellidosContacto);
        etTelefonoContacto = getView().findViewById(R.id.telefonoContacto);
        //Por aqui te has quedado
        etSexoContacto = getView().findViewById(R.id.tvSexo);
        etEmailContacto = getView().findViewById(R.id.botonNuevoContacto);
        etEstudiosContacto = getView().findViewById(R.id.botonNuevoContacto);
        etProvinciaContacto = getView().findViewById(R.id.botonNuevoContacto);
        etEdadContacto = getView().findViewById(R.id.botonNuevoContacto);

        String nombreContacto = etNombreContacto.getText().toString();
        String apellidoContacto = etApellidoContacto.getText().toString();
        String telefonoContacto = etTelefonoContacto.getText().toString();
        String sexoContacto = etSexoContacto.toString();
        String emailContacto = etEmailContacto.getText().toString();
        String estudios = obtenerInformacionMACTextViewEstudios();
        String provincia = obtenerInformacionSpinnerProvincia();
        int edad = obtenerInformacionSeekbarEdad();

        listaPersonas.add(new Persona(nombreContacto, apellidoContacto, telefonoContacto, sexoContacto, emailContacto, estudios, provincia, edad));
    }

    private String obtenerInformacionMACTextViewEstudios(){
        String estudios = etEstudiosContacto.getText().toString();
        String remplazado = estudios.replace(",","").trim().replace(" ",",");
        return remplazado;
    }

    private String obtenerInformacionSpinnerProvincia(){
        return etProvinciaContacto.getSelectedItem().toString();
    }

    private int obtenerInformacionSeekbarEdad(){
        return etEdadContacto.getProgress();
    }
}
