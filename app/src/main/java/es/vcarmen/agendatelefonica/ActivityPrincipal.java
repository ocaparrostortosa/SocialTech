package es.vcarmen.agendatelefonica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityPrincipal extends AppCompatActivity {

    private EditText listaContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        new Controlador(this, new PersonaDAO());
    }

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

}
