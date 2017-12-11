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

/**
 *
 */
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
    }

    private void setActionBarPersonalStyle(){
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        Typeface tpf = Typeface.createFromAsset(getAssets(), "fonts/Login.ttf");
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(tpf);

        setSupportActionBar(toolbar);
    }

    private void inicialize(){
        setActionBarPersonalStyle();

        fragmento1 = new Fragmento1Empresas(empresaDAO);
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.contenedor, fragmento1).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     *
     * @param fragmento
     */
    public void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contenedor, fragmento).commit();

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
                resultado = true;
                break;
            case R.id.action_persona:
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

        myRef.child("listaEmpresas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if((dataSnapshot.getValue()) != null)
                    listaEmpresas = (ArrayList<Object>) dataSnapshot.getValue();
                if(!listaEmpresas.isEmpty())
                    empresaDAO.actualizarEmpresas(listaEmpresas);
                switch (fragmentoActual) {
                    case NOMBRE_FRAGMENTO_LISTA_EMPRESAS:
                        reemplazarFragmentoPrincipal(new Fragmento1Empresas(empresaDAO));
                        break;
                    case NOMBRE_FRAGMENTO_NUEVO_EMPRESA:
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
        listaEmpresas = new ArrayList<>();

        myRef.child("listaEmpresas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if((dataSnapshot.getValue()) != null) {
                    listaEmpresas = (ArrayList<Object>) dataSnapshot.getValue();
                }
                if(!listaEmpresas.isEmpty())
                    empresaDAO.actualizarEmpresas(listaEmpresas);

                inicialize();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
