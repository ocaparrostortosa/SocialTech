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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_empresa, parent, false);

        ImageView imagenEmpresa = convertView.findViewById(R.id.imagenEmpresa);
        TextView nombre = (TextView) convertView.findViewById(R.id.tvName);
        TextView localidad = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView telefono = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView email = (TextView) convertView.findViewById(R.id.tvEmail);

        Log.v("FirebaseEmail", "EmpresaAdap:getView():Estado objeto:" + objeto);
        if(objeto != null) {
            nombre.setText(objeto.get("nombreEmpresa").toString());
            Log.v("EmpresaAdapter", "EmpresaAdap:getView():Contenido nombre:" + objeto.get("nombreEmpresa").toString());
            localidad.setText(objeto.get("localidadEmpresa").toString());
            telefono.setText(objeto.get("telefonoCorporativo").toString());
            email.setText(objeto.get("emailCorporativo").toString());
            //long valor = Long.parseLong(objeto.get("imagen"));
            Log.v("EmpresaAdapter", "EmpresaAdap:getView():Tipo objeto de 'imagen':" + String.valueOf(objeto.get("foto")) + ":int:" + R.drawable.business_icon1);
            imagenEmpresa.setImageDrawable(convertView.getResources().getDrawable(Integer.parseInt(String.valueOf(objeto.get("foto"))), contexto.getTheme()));
        }else{
            Log.v("EmpresaAdapter", "EmpresaAdap:getView():Error estado objeto:" + objeto);
        }

        return convertView;
    }


}
