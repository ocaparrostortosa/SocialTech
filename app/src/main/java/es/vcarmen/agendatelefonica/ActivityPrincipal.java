package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    private void inicialize(){
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Controlar que el usuario no pueda usar el menú si aún no se ha iniciado sesión.
        personaDAO = new PersonaDAO();
        int idSeleccionado = item.getItemId();
        switch (idSeleccionado){
            case R.id.menuOpcionNuevoContacto:
                obtenerDatosFirebase();
                reemplazarFragmentoPrincipal(new Fragmento2(personaDAO));
                return true;
            case R.id.menuOpcionListaContactos:
                obtenerDatosFirebase();
                reemplazarFragmentoPrincipal(new Fragmento1(personaDAO));
                return true;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void obtenerDatosFirebase(){
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
