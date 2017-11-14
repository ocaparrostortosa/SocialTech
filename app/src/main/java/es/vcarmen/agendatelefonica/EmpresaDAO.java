package es.vcarmen.agendatelefonica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by OSCAR on 14/11/2017.
 */

public class EmpresaDAO implements Serializable {
    private ArrayList<Empresa> listaEmpresas = new ArrayList<Empresa>();

    public void addEmpresa(Empresa empresa){
        listaEmpresas.add(empresa);
    }

    public void removeEmpresa(Empresa empresa){
        listaEmpresas.remove(empresa);
    }

    public ArrayList<Empresa> mostrarEmpresa(){
        return listaEmpresas;
    }
}
