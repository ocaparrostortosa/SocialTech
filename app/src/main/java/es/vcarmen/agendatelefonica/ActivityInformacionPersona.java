package es.vcarmen.agendatelefonica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by matinal on 17/10/2017.
 */

public class ActivityInformacionPersona extends AppCompatActivity {

    private Persona persona;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);
        inicialize();

    }

    private void inicialize(){
        persona = (Persona) getIntent().getExtras().getSerializable("objetoPersona");

        TextView nombre = (TextView) findViewById(R.id.tvName);
        TextView apellidos = (TextView) findViewById(R.id.tvSurname);
        TextView email = (TextView) findViewById(R.id.tvEmail);
        TextView sexo = (TextView) findViewById(R.id.tvSexo);
        TextView telefono = (TextView) findViewById(R.id.tvTelefono);
        TextView estudios = (TextView) findViewById(R.id.tvEstudios);
        TextView provincia = (TextView) findViewById(R.id.tvProvincia);
        TextView edad = (TextView) findViewById(R.id.tvEdad);

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
