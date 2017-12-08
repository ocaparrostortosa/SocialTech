package es.vcarmen.agendatelefonica;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento2Empresas extends DialogFragment {

    private Button botonAlta;
    private Button botonBorrar;
    private EditText etNombreEmpresa;
    private EditText etDireccionEmpresa;
    private EditText etTelefonoCorporativo;
    private EditText etLocalidadEmpresa;
    private EditText etEmailCorporativo;
    private MultiAutoCompleteTextView macContactoAsociado;
    private Spinner spProvinciaContacto;
    private EditText etObservaciones;
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private ArrayList<Object> listaEmpresas;

    private View vista;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("empresaDAO", empresaDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.vista = container;
        if(savedInstanceState != null){
            empresaDAO = (EmpresaDAO) savedInstanceState.getSerializable("empresaDAO");
        }
        accionesFirebase();
        return inflater.inflate(R.layout.layout_fragmento2empresas, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    public Fragmento2Empresas(){}

    public Fragmento2Empresas(EmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO;
        this.listaEmpresas = empresaDAO.mostrarEmpresas();
    }

    private void initialize(){
        inicializarVariables();
        botonAlta = (Button)getView().findViewById(R.id.botonAlta);
        botonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionBotonAlta();
                //((ActivityPrincipal)getActivity()).accionBotonAlta();
            }
        });
    }

    private void inicializarVariables(){
        etNombreEmpresa = getView().findViewById(R.id.etNombreEmpresa);
        etDireccionEmpresa = getView().findViewById(R.id.etDireccionEmpresa);
        etTelefonoCorporativo = getView().findViewById(R.id.etTelefonoCorporativo);
        etEmailCorporativo = getView().findViewById(R.id.etEmailCorporativo);
        etLocalidadEmpresa = getView().findViewById(R.id.etLocalidadEmpresa);
        macContactoAsociado = getView().findViewById(R.id.macContactoAsociado);
        rellenarMACTextViewContactoAsociado();
        spProvinciaContacto = getView().findViewById(R.id.spinnerProvincia);
        rellenarSpinnerProvincias();
        etObservaciones = getView().findViewById(R.id.etObservaciones);
    }

    private void accionBotonAlta(){
        String nombreEmpresa = etNombreEmpresa.getText().toString();
        String direccionEmpresa = etDireccionEmpresa.getText().toString();
        String telefonoCorporativo = etTelefonoCorporativo.getText().toString();
        String localidadEmpresa = etLocalidadEmpresa.getText().toString();
        String emailCorporativo = etEmailCorporativo.getText().toString();
        String contactoAsociado = obtenerInformacionMACTextViewEstudios();
        String provinciaEmpresa = obtenerInformacionSpinnerProvincia();
        String observaciones = etObservaciones.getText().toString();
        int foto = obtenerImagenContacto(localidadEmpresa);

        if(nombreEmpresa.equals("") && telefonoCorporativo.equals("")){
            Snackbar.make(vista, "FALTAN CAMPOS OBLIGATORIOS(*)", Snackbar.LENGTH_SHORT).show();
        }else{
            Log.v("F2Empresas","F2Empresas:accionBotonAlta():estadoLista:" + listaEmpresas);
            Empresa empresaAMeter = new Empresa(nombreEmpresa, direccionEmpresa, telefonoCorporativo, localidadEmpresa, emailCorporativo, contactoAsociado, provinciaEmpresa, observaciones, foto);
            empresaDAO.addEmpresaFireBase(empresaAMeter, empresaDAO, (ActivityPrincipalEmpresas) getActivity());
        }
    }

    private String obtenerInformacionMACTextViewEstudios(){
        String contacto = macContactoAsociado.getText().toString();
        return contacto.replace(",","").trim().replace(" ",",");
    }

    private String obtenerInformacionSpinnerProvincia(){
        return spProvinciaContacto.getSelectedItem().toString();
    }

    private void rellenarSpinnerProvincias(){
        String[] provincias = {"Jaén","Almería","Málaga","Sevilla","Granada","Huelva","Córdoba", "Almería"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item, provincias);
        spProvinciaContacto.setAdapter(arrayAdapter);
    }

    private void rellenarMACTextViewContactoAsociado(){
        //REFACTORIZAR PARA AÑADIR A LA COLECCION LOS NOMBRES DE LOS CONTACTOS
        String[] contactos = {"Contacto1","Contacto1","Contacto1","Contacto1","Contacto1","Contacto1","Contacto1"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item, contactos);
        macContactoAsociado.setAdapter(arrayAdapter);
        macContactoAsociado.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void cambiarDeFragmentoPasandoLista(EmpresaDAO empresaDAO){
        Log.v("F2Empresas",empresaDAO.mostrarEmpresas().toString());
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1Empresas(empresaDAO));
    }

    private int obtenerImagenContacto(String sexoContacto){
        int imagen = R.drawable.business_icon1;
        return imagen;
    }

    private void accionesFirebase() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();
        Log.d("F2Empresas", "Has entrado en Fragmento2Empresas:accionesFirebase():" + usuario.getEmail() + ":Id:" + usuario.getUid());
    }

}
