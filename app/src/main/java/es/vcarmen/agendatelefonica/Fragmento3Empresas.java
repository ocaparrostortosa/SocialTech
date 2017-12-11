package es.vcarmen.agendatelefonica;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by OSCAR on 18/10/2017.
 */
public class Fragmento3Empresas extends Fragment {

    private ArrayList<Object> listaEmpresas;
    private View vista;
    private int posicion;

    /**
     *
     */
    public Fragmento3Empresas(){}

    /**
     *
     * @param listaEmpresas
     * @param posicion
     */
    public Fragmento3Empresas(ArrayList<Object> listaEmpresas, int posicion){
        this.listaEmpresas = listaEmpresas;
        this.posicion = posicion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.layout_fragmento3empresas, container, false);
        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        HashMap<String, String> objeto = (HashMap<String, String>) listaEmpresas.get(posicion);

        TextView nombreEmpresa = (TextView) getView().findViewById(R.id.tvNombre);
        TextView direccionEmpresa = (TextView) getView().findViewById(R.id.tvDireccion);
        TextView emailCorporativo = (TextView) getView().findViewById(R.id.tvEmail);
        TextView localidadEmpresa = (TextView) getView().findViewById(R.id.tvLocalidad);
        TextView telefonoCorporativo = (TextView) getView().findViewById(R.id.tvTelefono);
        TextView contactoAsociado = (TextView) getView().findViewById(R.id.tvContacto);
        TextView provinciaEmpresa = (TextView) getView().findViewById(R.id.tvProvincia);
        TextView observaciones = (TextView) getView().findViewById(R.id.tvObservaciones);
        ImageView foto = getView().findViewById(R.id.imagenEmpresa);

        nombreEmpresa.append(objeto.get("nombreEmpresa").toString());
        direccionEmpresa.append(objeto.get("direccionEmpresa").toString());
        emailCorporativo.append(objeto.get("emailCorporativo").toString());
        localidadEmpresa.append(objeto.get("localidadEmpresa").toString());
        telefonoCorporativo.append(objeto.get("telefonoCorporativo").toString());
        contactoAsociado.append(objeto.get("contactoAsociado").toString());
        provinciaEmpresa.append(objeto.get("provinciaEmpresa").toString());
        observaciones.setText(String.valueOf(objeto.get("observaciones")));
        foto.setImageDrawable(getView().getResources().getDrawable(Integer.parseInt(String.valueOf(objeto.get("foto"))), getActivity().getApplicationContext().getTheme()));

        accionImagenContacto(foto, objeto.get("telefonoCorporativo").toString());
    }

    private void accionImagenContacto(ImageView imagen, final String telefono){
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionSnackBarImagenContacto(telefono);
            }
        });
    }

    private void accionSnackBarImagenContacto(final String telefono){
        Snackbar snackbar = Snackbar.make(vista, "¿LLAMAR A: " + telefono, Snackbar.LENGTH_LONG);
        snackbar.setAction("ACEPTAR", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + telefono));
                startActivity(callIntent);
            }
        });
        snackbar.show();
    }
}
