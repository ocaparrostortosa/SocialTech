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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class FragmentoLogin extends Fragment {

    FloatingActionButton boton;
    private Activity activity;
    private PersonaDAO personaDAO = new PersonaDAO();
    private ArrayList<Persona> listaPersonas;
    private AlertDialog.Builder dialogo;
    private View view;
    private Button botonLogin;
    private TextView botonRegistro;
    private CardView cardView;
    private ProgressBar barraProgreso;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public FragmentoLogin(){}

    public FragmentoLogin(PersonaDAO personaDAO){
        this.personaDAO = personaDAO;
    }

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

    }

    private void accionBotonLogin(){
        obtenerDatosYHacerLogin();

        barraProgreso.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.INVISIBLE);

        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1());
    }

    private void obtenerDatosYHacerLogin(){
        EditText etEmail = view.findViewById(R.id.email_usuario);
        EditText etClave = view.findViewById(R.id.campo_password);

        String emailUsuario = etEmail.getText().toString();
        String claveUsuario = etClave.getText().toString();

        //CREAR MÉTODO PARA LOGIN Y CAMBIARLO POR ESTE---------------------------------------------
        //accionBotonRegistro(emailUsuario, claveUsuario);

    }

    private void accionBotonRegistro(){
        //Abrir activity registro
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new FragmentoRegistro());
    }

    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    //usuario ya logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_logueado:" + usuario.getUid());
                }else{
                    //usuario no logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
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