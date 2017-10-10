package es.vcarmen.agendatelefonica;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matinal on 03/10/2017.
 */

public class Controlador {
    private ActivityPrincipal activityPrincipal;
    private PersonaDAO personaDAO;
    private List<Persona> listaPersonas = new ArrayList<Persona>();

    public Controlador(ActivityPrincipal activityPrincipal, PersonaDAO personaDAO) {
        this.activityPrincipal = activityPrincipal;
        this.personaDAO = personaDAO;
        accionBotonAlta();
        accionBotonBorrar();
        accionNumeroTotalContactos();
    }

    public void recogerInformacionEditText() {
        String nombreContacto = activityPrincipal.getEditTextNombre().getText().toString();
        String apellidoContacto = activityPrincipal.getEditTextApellidos().getText().toString();
        String telefonoContacto = activityPrincipal.getEditTextTelefono().getText().toString();
        String sexoContacto = activityPrincipal.getEditTextSexo().getText().toString();
        String emailContacto = activityPrincipal.getEditTextEmail().getText().toString();

        listaPersonas.add(new Persona(nombreContacto, apellidoContacto, telefonoContacto, sexoContacto, emailContacto));
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
                activityPrincipal.getListaContactos().setAdapter(new ArrayAdapter<String>(activityPrincipal.getApplicationContext(), android.R.layout.simple_list_item_1, obtenerContactos()));
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

    private List<String> obtenerContactos() {
        List<String> lista = new ArrayList<String>();

        for (Persona s : listaPersonas ) {
            //lista += s.getNombre() + ":" + s.getApellidos() + ":" + s.getTelefono() + ":" + s.getSexo() + "\n";
            lista.add(s.toString());
        }
        //activityPrincipal.getListaContactos().setText(listaPersonas.get(0).toString());

        return lista;
    }

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

}
