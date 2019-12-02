package com.example.bacelonatours;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Timer;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactoFragment extends Fragment {

    DatePicker datePicker1;
    SharedPreferences prefs;

    public ContactoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //prefs = getPreferences(MODE_PRIVATE);

//        datePicker1 = view.findViewById(R.id.datePicker1);
//        datePicker1.updateDate(
//                prefs.getInt("DATEPICKER1_YEAR", datePicker1.getYear()),
//                prefs.getInt("DATEPICKER1_MONTH", datePicker1.getMonth()),
//                prefs.getInt("DATEPICKER1_DAYOFMONTH", datePicker1.getDayOfMonth()));
//
//        datePicker1.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//                prefs.edit()
//                        .putInt("DATEPICKER1_YEAR", i)
//                        .putInt("DATEPICKER1_MONTH", i1)
//                        .putInt("DATEPICKER1_DAYOFMONTH", i2)
//                        .apply();
//            }
//        });
    }
}
