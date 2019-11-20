package com.example.bacelonatours;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.Tour;
import com.example.bacelonatours.model.TourDetail;

public class DetailFragment extends Fragment {

    MainViewModel mainViewModel;

    TextView titulo, descripcion;
    ImageView imagenDetail;

    Tour tour;

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        titulo = view.findViewById(R.id.probando);
        imagenDetail = view.findViewById(R.id.imageDetail);

        mainViewModel.tour.observe(getViewLifecycleOwner(), new Observer<Tour>() {
            @Override
            public void onChanged(Tour tour) {
                mostrarDetalleDelTour(tour);
            }
        });
    }

    private void mostrarDetalleDelTour(Tour tour) {
        mainViewModel.obtenerTourDetail(tour.tourId).observe(getViewLifecycleOwner(), new Observer<TourDetail>() {
            @Override
            public void onChanged(TourDetail tourDetail) {
                titulo.setText(tourDetail.tourName);
                Glide.with(requireActivity()).load(tourDetail.tourImage).into(imagenDetail);

            }
        });
    }
}
