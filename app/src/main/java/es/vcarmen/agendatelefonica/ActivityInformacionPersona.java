package es.vcarmen.agendatelefonica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matinal on 17/10/2017.
 */

public class ActivityInformacionPersona extends AppCompatActivity {

    private Persona persona;
    @BindView(R.id.tvName) TextView nombre;
    @BindView(R.id.tvSurname) TextView apellidos;
    @BindView(R.id.tvEmail) TextView email;
    @BindView(R.id.tvSexo) TextView sexo;
    @BindView(R.id.tvTelefono) TextView telefono;
    @BindView(R.id.tvEstudios) TextView estudios;
    @BindView(R.id.tvProvincia) TextView provincia;
    @BindView(R.id.tvEdad) TextView edad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);
        ButterKnife.bind(this);
        inicialize();

    }

    private void inicialize(){
        persona = (Persona) getIntent().getExtras().getSerializable("objetoPersona");

        nombre.append(persona.getNombre()+"");
        apellidos.append(persona.getApellidos()+"");
        email.append(persona.getEmail()+"");
        sexo.append(persona.getSexo()+"");
        telefono.append(persona.getTelefono()+"");
        estudios.append(persona.getEstudios()+"");
        provincia.append(persona.getProvincia()+"");
        edad.append(persona.getEdad()+"");

    }
}
