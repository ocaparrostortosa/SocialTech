package es.vcarmen.agendatelefonica;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.res.Resources;
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
<<<<<<< HEAD
=======
import android.support.v4.content.ContextCompat;
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matinal on 17/10/2017.
 */

public class PersonaAdapter extends ArrayAdapter<Object> {
<<<<<<< HEAD

    ArrayList<Object> lista = new ArrayList<>();
    Context contexto;

    public PersonaAdapter(@NonNull Context context, ArrayList<Object> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.contexto = context;
=======
    private ArrayList<Object> lista = new ArrayList<>();

    public PersonaAdapter(@NonNull Context context, ArrayList<Object> lista) {
        super(context, 0, lista);
        this.lista = lista;
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
<<<<<<< HEAD
        HashMap<String, String> objeto = (HashMap<String, String>) lista.get(position);
=======
        HashMap<String, String> o = (HashMap<String, String>) lista.get(position);
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        ImageView imagenContacto = convertView.findViewById(R.id.imagenContacto);
        TextView nombre = (TextView) convertView.findViewById(R.id.tvName);
        TextView apellidos = (TextView) convertView.findViewById(R.id.tvSurname);
        TextView telefono = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView email = (TextView) convertView.findViewById(R.id.tvEmail);

<<<<<<< HEAD
        Log.v("FirebaseEmail", "PersonaAdap:getView():Estado objeto:" + objeto);
        if(objeto != null) {
            nombre.setText(objeto.get("nombre").toString());
            Log.v("FirebaseEmail", "PersonaAdap:getView():Contenido nombre:" + objeto.get("nombre").toString());
            apellidos.setText(objeto.get("apellidos").toString());
            telefono.setText(objeto.get("telefono").toString());
            email.setText(objeto.get("email").toString());
            //long valor = Long.parseLong(objeto.get("imagen"));
            Log.v("FirebaseEmail", "PersonaAdap:getView():Tipo objeto de 'imagen':" + String.valueOf(objeto.get("imagen")) + ":int:" + R.drawable.user_icon8);
            imagenContacto.setImageDrawable(convertView.getResources().getDrawable(Integer.parseInt(String.valueOf(objeto.get("imagen"))), contexto.getTheme()));
        }else{
            Log.v("FirebaseEmail", "PersonaAdap:getView():Error estado objeto:" + objeto);
        }
=======
        switch (o.get("sexo")){
            case "hombre":
                imagenContacto.setImageResource(R.drawable.user_icon6);
                break;
            case "mujer":
                imagenContacto.setImageResource(R.drawable.user_icon7);
                break;
            case "otro":
                imagenContacto.setImageResource(R.drawable.user_icon8);
                break;
            default:
                imagenContacto.setImageResource(R.drawable.user_icon8);
                break;
        }
        nombre.setText(o.get("nombre"));
        apellidos.setText(o.get("apellidos"));
        telefono.setText(o.get("telefono"));
        email.setText(o.get("email"));
>>>>>>> 2e808d5a8313a680b70b950c196b5e38404e6e94

        return convertView;
    }


}
