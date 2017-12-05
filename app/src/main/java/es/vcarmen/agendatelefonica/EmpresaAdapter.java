package es.vcarmen.agendatelefonica;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matinal on 17/10/2017.
 */

public class EmpresaAdapter extends ArrayAdapter<Object> {

    ArrayList<Object> lista = new ArrayList<>();
    Context contexto;

    public EmpresaAdapter(@NonNull Context context, ArrayList<Object> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HashMap<String, String> objeto = (HashMap<String, String>) lista.get(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        ImageView imagenContacto = convertView.findViewById(R.id.imagenContacto);
        TextView nombre = (TextView) convertView.findViewById(R.id.tvName);
        TextView apellidos = (TextView) convertView.findViewById(R.id.tvSurname);
        TextView telefono = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView email = (TextView) convertView.findViewById(R.id.tvEmail);

        Log.v("FirebaseEmail", "EmpresaAdap:getView():Estado objeto:" + objeto);
        if(objeto != null) {
            nombre.setText(objeto.get("nombre").toString());
            Log.v("FirebaseEmail", "EmpresaAdap:getView():Contenido nombre:" + objeto.get("nombre").toString());
            apellidos.setText(objeto.get("apellidos").toString());
            telefono.setText(objeto.get("telefono").toString());
            email.setText(objeto.get("email").toString());
            //long valor = Long.parseLong(objeto.get("imagen"));
            Log.v("FirebaseEmail", "EmpresaAdap:getView():Tipo objeto de 'imagen':" + String.valueOf(objeto.get("imagen")) + ":int:" + R.drawable.user_icon8);
            /////////imagenContacto.setImageDrawable(convertView.getResources().getDrawable(Integer.parseInt(String.valueOf(objeto.get("imagen"))), contexto.getTheme()));
        }else{
            Log.v("FirebaseEmail", "EmpresaAdap:getView():Error estado objeto:" + objeto);
        }

        return convertView;
    }


}
