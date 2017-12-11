package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
 *  ActivityPrincipal is the main activity which initializes the application and makes the swap between the fragments.
 */
public class ActivityPrincipal extends AppCompatActivity {

    private Fragment fragmento1;
    private ArrayList<Object> listaPersonas;
    private PersonaDAO personaDAO;
    private Toolbar toolbar;
    private Menu menu;
    private boolean estado = false;
    private String fragmentoActual;
    private static final String NOMBRE_FRAGMENTO_LISTA_CONTACTOS = "class es.vcarmen.agendatelefonica.Fragmento1";
    private static final String NOMBRE_FRAGMENTO_NUEVO_CONTACTO = "class es.vcarmen.agendatelefonica.Fragmento2";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inicialize();
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

        fragmento1 = new FragmentoLogin();
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.contenedor, fragmento1).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Method that replaces the fragment to show with a transition.
     * @param fragmento Fragment to replace.
     */
    public void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contenedor, fragmento).commit();
        fragmentoActual = fragmento.getClass() + "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        if(estado) {
            getMenuInflater().inflate(R.menu.menu_opciones, menu);
            estado = true;
        }
        return estado;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean resultado;
        personaDAO = new PersonaDAO();
        int idSeleccionado = item.getItemId();
        switch (idSeleccionado){
            case R.id.menuOpcionNuevoContacto:
                obtenerDatosFirebase(NOMBRE_FRAGMENTO_NUEVO_CONTACTO);
                resultado = true;
                break;
            case R.id.menuOpcionListaContactos:
                obtenerDatosFirebase(NOMBRE_FRAGMENTO_LISTA_CONTACTOS);
                resultado = true;
                break;
            case R.id.action_empresa:
                Intent i = new Intent(this, ActivityPrincipalEmpresas.class);
                startActivity(i);
                resultado = true;
                break;
            case R.id.action_persona:
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

        myRef.child("listaPersonas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if((dataSnapshot.getValue()) != null)
                    listaPersonas = (ArrayList<Object>) dataSnapshot.getValue();
                if(!listaPersonas.isEmpty())
                    personaDAO.actualizarPersonas(listaPersonas);
                switch (fragmentoActual) {
                    case NOMBRE_FRAGMENTO_LISTA_CONTACTOS:
                        reemplazarFragmentoPrincipal(new Fragmento1(personaDAO));
                        break;
                    case NOMBRE_FRAGMENTO_NUEVO_CONTACTO:
                        reemplazarFragmentoPrincipal(new Fragmento2(personaDAO));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     *  Default get method to return the menu of the activity.
     * @return Current activity's menu.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Default get method to return the state of the activity's menu.
     * @return Menu's state.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Default set method to set the state of the activity's menu.
     * @param estado Boolean value.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
