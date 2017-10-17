package es.vcarmen.agendatelefonica;

import android.icu.util.Output;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matinal on 03/10/2017.
 */

public class Controlador {
    private ActivityPrincipal activityPrincipal;
    private PersonaDAO personaDAO;
    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

    public Controlador(ActivityPrincipal activityPrincipal, PersonaDAO personaDAO) {
        this.activityPrincipal = activityPrincipal;
        this.personaDAO = personaDAO;
        inicialize();

    }

    private void inicialize(){
        accionBotonAlta();
        accionBotonBorrar();
        accionNumeroTotalContactos();
        accionSeekbarEdad();
        rellenarMACTextViewEstudios();
        rellenarSpinnerProvincias();
        seleccionarUsuarioLista();
    }

    private void seleccionarUsuarioLista(){
        activityPrincipal.getListaContactos().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void rellenarMACTextViewEstudios(){
        MultiAutoCompleteTextView macTextView = (MultiAutoCompleteTextView) activityPrincipal.findViewById(R.id.macEstudios);
        String[] estudios = {"SMR","DAM","DAW","ASIR","Ingeniería técnica informática","Grado","Otros"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(activityPrincipal.getApplicationContext(), R.layout.spinner_item, estudios);
        macTextView.setAdapter(arrayAdapter);
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void rellenarSpinnerProvincias(){
        Spinner spinner = (Spinner) activityPrincipal.findViewById(R.id.spinnerProvincia);
        String[] provincias = {"Jaén","Almería","Málaga","Sevilla","Granada","Huelva","Córdoba", "Almería"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(activityPrincipal.getApplicationContext(), R.layout.spinner_item, provincias);
        spinner.setAdapter(arrayAdapter);
    }

    public void recogerInformacionEditText() {
        String nombreContacto = activityPrincipal.getEditTextNombre().getText().toString();
        String apellidoContacto = activityPrincipal.getEditTextApellidos().getText().toString();
        String telefonoContacto = activityPrincipal.getEditTextTelefono().getText().toString();
        String sexoContacto = activityPrincipal.getEditTextSexo().getText().toString();
        String emailContacto = activityPrincipal.getEditTextEmail().getText().toString();
        String estudios = obtenerInformacionMACTextViewEstudios();
        String provincia = obtenerInformacionSpinnerProvincia();
        int edad = obtenerInformacionSeekbarEdad();

        listaPersonas.add(new Persona(nombreContacto, apellidoContacto, telefonoContacto, sexoContacto, emailContacto, estudios, provincia, edad));
    }

    public int obtenerInformacionSeekbarEdad(){
        return activityPrincipal.getSeekbarEdad().getProgress();
    }

    public String obtenerInformacionMACTextViewEstudios(){
        String estudios = activityPrincipal.getMACTextViewEstudios().getText().toString();
        String remplazado = estudios.replace(",","").trim().replace(" ",",");
        return remplazado;
    }

    public String obtenerInformacionSpinnerProvincia(){
        return activityPrincipal.getSpinnerProvincias().getSelectedItem().toString();
    }

    public void eliminarInformacionEditText(){
        activityPrincipal.getEditTextNombre().setText("");
        activityPrincipal.getEditTextApellidos().setText("");
        activityPrincipal.getEditTextTelefono().setText("");
        activityPrincipal.getEditTextEmail().setText("");
        if(activityPrincipal.getEditTextSexo().isChecked()) {
            activityPrincipal.getEditTextSexo().setChecked(false);
            activityPrincipal.getEditTextSexo().setChecked(false);
            activityPrincipal.getEditTextSexo().setChecked(false);
        }
    }


    public void accionBotonAlta() {
        activityPrincipal.getBotonAlta().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recogerInformacionEditText();
                //activityPrincipal.getListaContactos().setText(obtenerContactos());
                activityPrincipal.getListaContactos().setAdapter(new PersonaAdapter(activityPrincipal.getApplicationContext(), listaPersonas));
                accionNumeroTotalContactos();
            }
        });
    }

    public void accionBotonBorrar(){
        activityPrincipal.getBotonBorrar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarInformacionEditText();
                restablecerRadioButton();
            }
        });
    }
    /**
    private List<String> obtenerContactos() {
        List<String> lista = new ArrayList<String>();

        for (Persona s : listaPersonas ) {
            //lista += s.getNombre() + ":" + s.getApellidos() + ":" + s.getTelefono() + ":" + s.getSexo() + "\n";
            lista.add(s.toString());
        }
        //activityPrincipal.getListaContactos().setText(listaPersonas.get(0).toString());

        return lista;
    }
     */

    private void restablecerRadioButton(){
        RadioButton RBHombre = (RadioButton) activityPrincipal.findViewById(R.id.radioButtonHombre);
        RBHombre.setEnabled(true);
        RadioButton RBMujer = (RadioButton) activityPrincipal.findViewById(R.id.radioButtonMujer);
        RBMujer.setEnabled(true);
        RadioButton RBOtro = (RadioButton) activityPrincipal.findViewById(R.id.radioButtonOtro);
        RBOtro.setEnabled(true);
    }

    private void accionNumeroTotalContactos(){
        int numero = listaPersonas.size();
        TextView numeroTotal = (TextView) activityPrincipal.findViewById(R.id.numeroTotalContactos);
        numeroTotal.setText("Total: " + numero);
    }

    private void accionSeekbarEdad(){
        final TextView edad = (TextView) activityPrincipal.findViewById(R.id.textViewEdad);
        SeekBar seekbarEdad = (SeekBar) activityPrincipal.findViewById(R.id.seekbarEdad);
        seekbarEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                edad.setText("Edad: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void guardarBundle(Bundle outState){
        outState.putSerializable("Lista", listaPersonas);
    }

    public void guardarListaPersonas(Bundle getState){
        listaPersonas = (ArrayList<Persona>) getState.getSerializable("Lista");
        activityPrincipal.getListaContactos().setAdapter(new PersonaAdapter(activityPrincipal.getApplicationContext(), listaPersonas));
    }



}
