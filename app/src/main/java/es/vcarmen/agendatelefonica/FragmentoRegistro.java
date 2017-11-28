package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
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

/**
 * Created by OSCAR on 18/10/2017.
 */

public class FragmentoRegistro extends Fragment {

    private Activity activity;
    private View view;
    private Button botonRegistro;
    private CardView cardView;
    private ProgressBar barraProgreso;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public FragmentoRegistro(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_registro, container, false);
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
        accionFabVueltraAtras();

        TextView textoRegistro = view.findViewById(R.id.textoRegistro);
        Typeface tpf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Login.ttf");
        textoRegistro.setTypeface(tpf);

        barraProgreso = view.findViewById(R.id.progressBar);
        cardView = view.findViewById(R.id.cardViewLogin);
        botonRegistro = view.findViewById(R.id.botonRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionBotonRegisterNow();
            }
        });

    }

    private void accionBotonRegisterNow(){
        obtenerDatosYRegistrarse();
    }

    private void obtenerDatosYRegistrarse(){
        EditText etUsuario = view.findViewById(R.id.email_usuario);
        EditText etClave = view.findViewById(R.id.campo_password);
        EditText etConfirmarClave = view.findViewById(R.id.campo_confirm_password);

        String nombreUsuario = etUsuario.getText().toString();
        String claveUsuario = etClave.getText().toString();
        String claveConfirmadaUsuario = etConfirmarClave.getText().toString();

        //CREAR MÉTODO PARA LOGIN Y CAMBIARLO POR ESTE---------------------------------------------
        if(claveUsuario.equals(claveConfirmadaUsuario))
            accionBotonRegistro(nombreUsuario, claveUsuario);
        else
            mostrarSnackbar("LAS CONTRASEÑAS NO COINCIDEN");

    }

    private void accionBotonRegistro(final String email, final String passwd){
        //Abrir activity registro
        mAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Firebase", "createUserWithEmail:onComplete:"+ email + ":" + passwd + ":" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    accionMalRegistro();
                }else{
                    accionBuenRegistro();
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
                    //usuario ya logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_logueado:" + usuario.getUid());
                }else{
                    //usuario no logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
    }

    private void accionBuenRegistro(){
        ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new Fragmento1());
        barraProgreso.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.INVISIBLE);
    }

    private void accionMalRegistro(){
        mostrarSnackbar("EL EMAIL ESTÁ REGISTRADO O NO ES VÁLIDO");
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

    private void accionFabVueltraAtras(){
        ImageButton backButton = view.findViewById(R.id.backFabButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPrincipal)getActivity()).reemplazarFragmentoPrincipal(new FragmentoLogin());
            }
        });

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
