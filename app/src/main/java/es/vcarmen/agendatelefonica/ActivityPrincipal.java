package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityPrincipal extends AppCompatActivity {

    private EditText listaContactos;
    private Controlador controlador;
    private Fragmento1 fragmento1;
    private Fragmento2 fragmento2;
    private Fragmento3 fragmento3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inicialize();
        /**
        controlador = new Controlador(this, new PersonaDAO());
        if(savedInstanceState != null)
            controlador.guardarListaPersonas(savedInstanceState);
         */

    }

    private void inicialize(){
        fragmento1 = new Fragmento1();
        reemplazarFragmentoPrincipal(fragmento1);
        accionFragmento1();
    }

    private void accionFragmento1(){
        Activity activity = fragmento1.getActivityFragmento1();
        System.out.println(activity + "..................");
        Button boton1 = (Button) activity.findViewById(R.id.botonNuevoContacto);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().add(R.id.contenedor, fragmento).commit();
    }

/**
    public EditText getEditTextNombre() {
        return (EditText) findViewById(R.id.nombreContacto);
    }

    public EditText getEditTextApellidos() {
        return (EditText) findViewById(R.id.apellidosContacto);
    }

    public EditText getEditTextTelefono() {
        return (EditText) findViewById(R.id.telefonoContacto);
    }

    public EditText getEditTextEmail() {
        return (EditText) findViewById(R.id.emailContacto);
    }

    public MultiAutoCompleteTextView getMACTextViewEstudios(){
        return (MultiAutoCompleteTextView) findViewById(R.id.macEstudios);
    }

    public Spinner getSpinnerProvincias(){
        return (Spinner) findViewById(R.id.spinnerProvincia);
    }

    public SeekBar getSeekbarEdad(){
        return (SeekBar) findViewById(R.id.seekbarEdad);
    }

    public RadioButton getEditTextSexo() {
        RadioButton RBHombre = (RadioButton) findViewById(R.id.radioButtonHombre);
        RadioButton RBMujer = (RadioButton) findViewById(R.id.radioButtonMujer);
        RadioButton RBOtro = (RadioButton) findViewById(R.id.radioButtonOtro);
        if( RBHombre.isChecked() ){
            return RBHombre;
        }
        if( RBMujer.isChecked() ) {
            return RBMujer;
        }
        if( RBOtro.isChecked() ) {
            return RBOtro;
        }
        else
            return RBOtro;
    }



    public Button getBotonAlta() {
        return (Button) findViewById(R.id.botonAlta);
    }

    public Button getBotonBorrar() {
        return (Button) findViewById(R.id.botonBorrar);
    }

    public ListView getListaContactos() {
        return (ListView) findViewById(R.id.listaContactos);
    }

    public Bundle getBundle(){ return this.getBundle(); }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        controlador.guardarBundle(outState);
    }
    */

}
