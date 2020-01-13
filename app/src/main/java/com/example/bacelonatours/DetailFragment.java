package com.example.bacelonatours;


import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.Tour;
import com.example.bacelonatours.model.TourDetail;

/**
 * Clase para los detalles de cada tour disponile
 */
public class DetailFragment extends Fragment {

    MainViewModel mainViewModel;
    AutenticacionViewModel autenticacionViewModel;

    private TextView titulo, descripcion, explain, preuDetailText;
    private Button imgFavoritos;
    private ImageView imagenDetail ;
    RatingBar ratingBar;


    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ratingBar = view.findViewById(R.id.ratingBarxml);
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast toast1 =
                        Toast.makeText(requireActivity(),
                                String.valueOf(rating), Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,450,190);
                toast1.show();
            }
        });

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        autenticacionViewModel = ViewModelProviders.of(requireActivity()).get(AutenticacionViewModel.class);

        titulo = view.findViewById(R.id.detail);
        preuDetailText = view.findViewById(R.id.preuDetail);
        descripcion = view.findViewById(R.id.tourResource);
        explain = view.findViewById(R.id.tourExplain);
        imagenDetail = view.findViewById(R.id.imageDetail);

        imagenDetail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(requireContext()).setTitle("\t\tINTERACCIÓN OnLongListener")
                        .setMessage("\t      ")
                        .setMessage("\t      ")
                        .setCancelable(true)
                        .create()
                        .show();
                return false;
            }

        });

        mainViewModel.tour.observe(getViewLifecycleOwner(), new Observer<Tour>() {
            @Override
            public void onChanged(Tour tour) {
                mostrarDetalleDelTour(tour);
            }
        });
        view.findViewById(R.id.meInteresa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mainViewModel.quiereRegistrarse = true;

                // Si ya esta registrado va directo a reservar
                autenticacionViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDeLaAutenticacion>() {
                    @Override
                    public void onChanged(AutenticacionViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                        switch (estadoDeLaAutenticacion){
                            case YA_AUTENTIFICADO:
                                Navigation.findNavController(view).navigate(R.id.reservarTourFragment);
                                break;
                            case NO_AUTENTICADO:
                                Navigation.findNavController(view).navigate(R.id.registrarseFragment);
                                break;
                        }
                    }
                });
            }
        });
    }

    private void mostrarDetalleDelTour(Tour tour) {
        mainViewModel.obtenerTourDetail(tour.tourId).observe(getViewLifecycleOwner(), new Observer<TourDetail>() {
            @Override
            public void onChanged(TourDetail tourDetail) {
                Log.e("ABCD", " lalacosa " + tourDetail.tourId);
                titulo.setText(tourDetail.tourName);
                Glide.with(requireActivity()).load(tourDetail.tourImage).into(imagenDetail);
                preuDetailText.setText(tourDetail.tourDescription);
                descripcion.setText(tourDetail.tourResource);
                explain.setText(tourDetail.tourExplain);
            }
        });
    }

}
