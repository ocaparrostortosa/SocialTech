package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

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
    private AutoCompleteTextView acContactoAsociado;
    private Spinner spProvinciaContacto;
    private EditText etObservaciones;
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private ArrayList<Object> listaEmpresas;
    private ArrayList<Object> listaPersonas;

    private Context context;
    private View vista;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

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
        botonBorrar = (Button)getView().findViewById(R.id.botonBorrar);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accionBotonBorrar();
            }
        });
    }

    private void inicializarVariables(){
        rellenarACTextViewContactoAsociado();
        etNombreEmpresa = getView().findViewById(R.id.etNombreEmpresa);
        etDireccionEmpresa = getView().findViewById(R.id.etDireccionEmpresa);
        etTelefonoCorporativo = getView().findViewById(R.id.etTelefonoCorporativo);
        etEmailCorporativo = getView().findViewById(R.id.etEmailCorporativo);
        etLocalidadEmpresa = getView().findViewById(R.id.etLocalidadEmpresa);
        acContactoAsociado = getView().findViewById(R.id.macContactoAsociado);
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
            Log.v("F2Empresas","F2Empresas:accionBotonAlta():EmpresaAMeter:" + empresaAMeter);
            empresaDAO.addEmpresaFireBase(empresaAMeter, empresaDAO, (ActivityPrincipalEmpresas) getActivity());
        }
    }

    private void accionBotonBorrar(){
        etNombreEmpresa.setText("");
        etDireccionEmpresa.setText("");
        etTelefonoCorporativo.setText("");
        etEmailCorporativo.setText("");
        acContactoAsociado.setText("");
        spProvinciaContacto.setSelection(0);
        etObservaciones.setText("");
        etLocalidadEmpresa.setText("");
    }

    private String obtenerInformacionMACTextViewEstudios(){
        String contacto = acContactoAsociado.getText().toString();
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

    private void rellenarACTextViewContactoAsociado(){
        context = getActivity().getApplicationContext();
        listaPersonas = new ArrayList<>();
        //REFACTORIZAR PARA AÑADIR A LA COLECCION LOS NOMBRES DE LOS CONTACTOS
        myRef.child("listaPersonas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] contactos;
                Log.d("F2Empresas", "Has entrado en Fragmento2Empresas:accionesFirebase():onDataChange");
                if((dataSnapshot.getValue()) != null)
                    listaPersonas = (ArrayList<Object>) dataSnapshot.getValue();
                if(listaPersonas.isEmpty()) {
                    contactos = new String[]{"Aún no existen contactos."};
                    ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.spinner_item, contactos);
                    acContactoAsociado.setAdapter(arrayAdapter);
                    acContactoAsociado.setHint(contactos[0]);
                }else
                    obtenerContactosDeLista(listaPersonas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("F2Empresas", "Has entrado en Fragmento2Empresas:accionesFirebase():onCancelled:Ha habido un error");
            }
        });
        //acContactoAsociado.setTokenizer(new AutoCompleteTextView.CommaTokenizer());
    }

    private void obtenerContactosDeLista(final ArrayList<Object> listaContactos){
        final String[] contactos = new String[listaContactos.size()];
        Log.v("F2Empresas","obtenerContactosDeLista(listaPersonas):Tamaño lista:" + listaContactos.size() + ":" + listaContactos.get(0).getClass());
        for(int i = 0; i < listaContactos.size(); i++){
            final int finalI = i;
            myRef.child("listaPersonas/" + i).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("F2Empresas", "Fragmento2Empresas:obtenerContactosDeLista():onDataChange:" + dataSnapshot.getValue().toString() + ":" + dataSnapshot.getValue().getClass());
                    HashMap<String, String> objeto = (HashMap<String, String>) dataSnapshot.getValue();
                    contactos[finalI] = objeto.get("nombre");
                    if(finalI == listaContactos.size() -1 ) {
                        Log.d("F2Empresas", "Fragmento2Empresas:obtenerContactosDeLista():contenidoContactos:" + contactos[finalI]);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.spinner_item, contactos);
                        acContactoAsociado.setAdapter(arrayAdapter);
                    }
                    if(contactos[0].equals("Aún no existen contactos.")) acContactoAsociado.setHint(contactos[0]);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("F2Empresas", "Fragmento2Empresas:obtenerContactosDeLista():onCancelled:Ha habido un error");
                }
            });
        }
        //return contactos;
    }
/**
    private void cambiarDeFragmentoPasandoLista(EmpresaDAO empresaDAO){
        Log.v("F2Empresas",empresaDAO.mostrarEmpresas().toString());
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1Empresas(empresaDAO));
    }
 */

    private int obtenerImagenContacto(String sexoContacto){
        int imagen = R.drawable.business_icon1;
        return imagen;
    }

    private void accionesFirebase() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        Log.d("F2Empresas", "Has entrado en Fragmento2Empresas:accionesFirebase():" + usuario.getEmail() + ":Id:" + usuario.getUid());
        myRef = database.getReference(usuario.getUid());
    }

}
