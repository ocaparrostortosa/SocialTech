package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityPrincipalEmpresas extends AppCompatActivity {

    private Fragment fragmento1;
    private ArrayList<Object> listaEmpresas;
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private Toolbar toolbar;
    private Menu menu;
    private boolean estado = false;
    private String fragmentoActual;
    private static final String NOMBRE_FRAGMENTO_LISTA_EMPRESAS = "class es.vcarmen.agendatelefonica.Fragmento1Empresas";
    private static final String NOMBRE_FRAGMENTO_NUEVO_EMPRESA = "class es.vcarmen.agendatelefonica.Fragmento2Empresas";

    private CardView cardView;
    private ProgressBar progressBar;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        obtenerDatosFirebase();
        //inicialize();
        /**
        controlador = new Controlador(this, new PersonaDAO());
        if(savedInstanceState != null)
            controlador.guardarListaPersonas(savedInstanceState);
         */

    }

    private void setActionBarPersonalStyle(){
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        Typeface tpf = Typeface.createFromAsset(getAssets(), "fonts/Login.ttf");
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(tpf);
        //toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleTextAppearance);
        setSupportActionBar(toolbar);
    }

    private void inicialize(){
        setActionBarPersonalStyle();

        fragmento1 = new Fragmento1Empresas(empresaDAO);
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.contenedor, fragmento1).commit();
        //getFragmentManager().beginTransaction().add(R.id.contenedor, new Fragmento1Empresa()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contenedor, fragmento).commit();
        Log.v("ActivityPrEmp","Fragmento actual: " + fragmento.getClass());
        fragmentoActual = fragmento.getClass() + "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_opciones_empresas, menu);
        estado = true;

        return estado;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Controlar que el usuario no pueda usar el menú si aún no se ha iniciado sesión.
        boolean resultado;
        empresaDAO = new EmpresaDAO();
        int idSeleccionado = item.getItemId();
        switch (idSeleccionado){
            case R.id.menuOpcionNuevaEmpresa:
                obtenerDatosFirebase(NOMBRE_FRAGMENTO_NUEVO_EMPRESA);
                resultado = true;
                break;
            case R.id.menuOpcionListaEmpresas:
                obtenerDatosFirebase(NOMBRE_FRAGMENTO_LISTA_EMPRESAS);
                resultado = true;
                break;
            case R.id.action_empresa:
                Log.v("ActivityPrEmp","ActivityPrincipal:onOptionItemSelected:Has pulsado a la empresa");
                resultado = true;
                break;
            case R.id.action_persona:
                Log.v("MenuPersonalizado","ActivityPrincipal:onOptionItemSelected:Has pulsado a la empresa");
                finish();
                resultado = true;
                break;
            default:
                super.onOptionsItemSelected(item);
                resultado = false;
                break;
        }

        return resultado;
    }

    private void obtenerDatosFirebase(final String fragmentoActual){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(""+usuario.getUid());

        Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():"+ usuario.getUid());

        myRef.child("listaEmpresas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaEmpresas = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():Longitud de la lista:"+listaEmpresas.size());
                if(!listaEmpresas.isEmpty())
                    empresaDAO.actualizarEmpresas(listaEmpresas);
                Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():Contenido lista en dao:"+ empresaDAO.mostrarEmpresas());
                switch (fragmentoActual) {
                    case NOMBRE_FRAGMENTO_LISTA_EMPRESAS:
                        Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():Case Lista_Empresas:");
                        reemplazarFragmentoPrincipal(new Fragmento1Empresas(empresaDAO));
                        break;
                    case NOMBRE_FRAGMENTO_NUEVO_EMPRESA:
                        Log.v("ActivityPrEmp", "ActivityP:obtenerDatosFirebase():Case Nueva_Empresas:");
                        reemplazarFragmentoPrincipal(new Fragmento2Empresas(empresaDAO));
                        break;
                    default:
                        break;
                }
                inicialize();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void obtenerDatosFirebase(){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(""+usuario.getUid());

        Log.v("FirebaseOscar", "ActivityPEmp:obtenerDatosFirebase():"+ usuario.getUid());

        myRef.child("listaEmpresas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseOscar", "ActivityPEmp:obtenerDatosFirebase():DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaEmpresas = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseOscar", "ActivityPEmp:obtenerDatosFirebase():Longitud de la lista:"+listaEmpresas.size());
                if(!listaEmpresas.isEmpty())
                    empresaDAO.actualizarEmpresas(listaEmpresas);
                Log.v("FirebaseOscar", "ActivityPEmp:obtenerDatosFirebase():Contenido lista en dao:"+ empresaDAO.mostrarEmpresas());

                inicialize();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void mostrarSnackbar(String mensaje){
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), mensaje , Snackbar.LENGTH_LONG);
        snackbar.setAction("ACEPTAR", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}
