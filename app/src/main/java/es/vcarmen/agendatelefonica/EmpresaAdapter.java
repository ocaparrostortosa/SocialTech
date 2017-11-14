package es.vcarmen.agendatelefonica;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by OSCAR on 14/11/2017.
 */

public class EmpresaAdapter extends ArrayAdapter<Empresa> {
    public EmpresaAdapter(@NonNull Context context, ArrayList<Empresa> listaEmpresas) {
        super(context, 0, listaEmpresas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Empresa empresa = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_empresa, parent, false);

        TextView nombre = (TextView) convertView.findViewById(R.id.tvName);
        TextView direccion = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView telefono = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView email = (TextView) convertView.findViewById(R.id.tvEmail);

        nombre.setText(empresa.getNombre());
        direccion.setText(empresa.getDireccion());
        telefono.setText(empresa.getTelefonoCorporativo());
        email.setText(empresa.getEmailCorporativo());

        return convertView;
    }
}
