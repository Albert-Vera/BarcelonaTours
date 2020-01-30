package com.example.bacelonatours;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.TourDetail;


/**
 * A simple {@link Fragment} subclass.
 */
// TODO esta clase revisala... esta anulada pero cosas

public class GastronomiaFragment extends Fragment {

    private ImageView imageVisiteAMida;
    private Button meInteresa;

    MainViewModel mainViewModel;

    private TextView titulo, descripcion, explain;

    public GastronomiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gastronomia, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titulo = view.findViewById(R.id.text_gastronomia);
        descripcion = view.findViewById(R.id.description_gastronomia);
        explain = view.findViewById(R.id.gatroxxxxx);
        imageVisiteAMida = view.findViewById(R.id.imageGastronomia);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);


        mostrarVisitesAMida(view);

    }

    private void mostrarVisitesAMida(final View view) {
        mainViewModel.obtenerTourDetail("t10").observe(getViewLifecycleOwner(), new Observer<TourDetail>() {
            @Override
            public void onChanged(TourDetail tourDetail) {
                Log.e("ABCD", " lalacosa 1" + tourDetail.tourId );
                titulo.setText(tourDetail.tourName);
                Glide.with(requireActivity()).load(R.drawable.sopar).into(imageVisiteAMida);
                descripcion.setText(tourDetail.tourDescription);
                explain.setText(tourDetail.tourExplain);

                view.findViewById(R.id.meInteresaaGastronomia).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                        Navigation.findNavController(view).navigate(R.id.reservarTourFragment);

                    }
                });
            }
        });
    }
}
