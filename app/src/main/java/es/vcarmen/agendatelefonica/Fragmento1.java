package es.vcarmen.agendatelefonica;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by OSCAR on 18/10/2017.
 */

public class Fragmento1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragmento1, container, false);
    }

    public Activity getActivityFragmento1(){
        return this.getActivity();
    }
}
