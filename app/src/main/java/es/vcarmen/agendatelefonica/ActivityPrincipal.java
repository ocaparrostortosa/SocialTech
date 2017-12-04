package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

        fragmento1 = new FragmentoLogin();
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.contenedor, fragmento1).commit();
        //getFragmentManager().beginTransaction().add(R.id.contenedor, new Fragmento1Empresa()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contenedor, fragmento).commit();
        Log.v("Fragmentos","Fragmento actual: " + fragmento.getClass());
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
        //Controlar que el usuario no pueda usar el menú si aún no se ha iniciado sesión.
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
                Log.v("MenuPersonalizado","ActivityPrincipal:onOptionItemSelected:Has pulsado a la empresa");
                resultado = true;
                break;
            case R.id.action_persona:
                Log.v("MenuPersonalizado","ActivityPrincipal:onOptionItemSelected:Has pulsado a la persona");
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

        Log.v("FirebaseEmail", "ActivityP:obtenerDatosFirebase():"+ usuario.getUid());

        myRef.child("listaPersonas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseEmail", "ActivityP:obtenerDatosFirebase():DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaPersonas = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseEmail", "ActivityP:obtenerDatosFirebase():Longitud de la lista:"+listaPersonas.size());
                if(!listaPersonas.isEmpty())
                    personaDAO.actualizarPersonas(listaPersonas);
                Log.v("FirebaseEmail", "ActivityP:obtenerDatosFirebase():Contenido lista en dao:"+personaDAO.mostrarPersonas());
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

    public Menu getMenu() {
        return menu;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
