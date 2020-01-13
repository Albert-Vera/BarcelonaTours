package com.example.bacelonatours;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservasRealizadasFragment extends Fragment {

    TextView subrrallar;
    public ReservasRealizadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservas_realizadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  SUBRAYAR TEXTO phone
        subrrallar = view.findViewById(R.id.reservasRealizadas_xml);
        SpannableString subrallar = new SpannableString("        Data                          Tour         ");
        subrallar.setSpan(new UnderlineSpan(), 0, subrallar.length(), 0);
        subrrallar.setText(subrallar);
    }
}
