package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by OSCAR on 18/10/2017.
 */
public class FragmentoLogin extends Fragment {

    FloatingActionButton boton;
    private Activity activity;
    private PersonaDAO personaDAO = new PersonaDAO();
    private ArrayList<Object> listaPersonas = new ArrayList<Object>();
    private AlertDialog.Builder dialogo;
    private View view;
    private Button botonLogin;
    private TextView botonRegistro;
    private CardView cardView;
    private ProgressBar barraProgreso;
    private ImageButton botonVerClave;
    private EditText etEmail;
    private EditText etClave;
    private boolean estadoClave = false;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     *
     */
    public FragmentoLogin(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_login, container, false);
        //Firebase
        accionesFirebase();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){

        TextView textoLogin = view.findViewById(R.id.textoRegistro);
        Typeface tpf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Login.ttf");
        textoLogin.setTypeface(tpf);

        barraProgreso = view.findViewById(R.id.progressBar);
        cardView = view.findViewById(R.id.cardViewLogin);
        botonLogin = view.findViewById(R.id.botonRegistro);
        botonRegistro = view.findViewById(R.id.tvRegistro);
        botonVerClave = view.findViewById(R.id.botonVerClave);
        etEmail = view.findViewById(R.id.email_usuario);
        etClave = view.findViewById(R.id.campo_password);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionBotonLogin();
            }
        });
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionBotonRegistro();
            }
        });
        botonVerClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadoClave = !estadoClave;
                Log.d("EstadoClave","Estado: " + estadoClave);
                if(estadoClave) {
                    botonVerClave.setImageResource(R.drawable.ic_action_visible_password);
                    etClave.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    botonVerClave.setImageResource(R.drawable.ic_action_hide_password);
                    etClave.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

    }

    private void accionBotonLogin(){
        obtenerDatosYHacerLogin();
    }

    private void obtenerDatosYHacerLogin(){
        String emailUsuario = etEmail.getText().toString();
        String claveUsuario = etClave.getText().toString();

        Pattern patron = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher elMatcher = patron.matcher(emailUsuario);

        if(!elMatcher.matches()) {
            mostrarSnackbar("EL E-MAIL NO ES VÁLIDO");
        }else if(claveUsuario.length() < 6){
            mostrarSnackbar("LA CONTRASEÑA NO ES VÁLIDA");
        }else {
            barraProgreso.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            accionBotonLogin(emailUsuario, claveUsuario);
        }

    }

    private void accionBotonRegistro(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new FragmentoRegistro());
    }

    private void accionBotonLogin(String user, String passwd){
        mAuth.signInWithEmailAndPassword(user,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    barraProgreso.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.INVISIBLE);
                    pasarAFragmento1();
                }else {
                    barraProgreso.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    mostrarSnackbar("E-MAIL O CONTRASEÑA INCORRECTOS");
                }
            }
        });
    }

    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    Log.d("Firebase", "onAuthStateChanged:usuario_logueado:" + usuario.getUid());
                }else{
                    Log.d("Firebase", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
    }

    private void pasarAFragmento1(){
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
                if(getActivity() != null)
                    ((ActivityPrincipal) getActivity()).reemplazarFragmentoPrincipal(new Fragmento1(personaDAO));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void mostrarSnackbar(String mensaje){
        final Snackbar snackbar = Snackbar.make(view, mensaje , Snackbar.LENGTH_LONG);
        snackbar.setAction("ACEPTAR", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }
}
