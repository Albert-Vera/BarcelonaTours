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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.Tour;
import com.example.bacelonatours.model.TourDetail;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisitesAMidaFragment extends Fragment {

    private ImageView imageVisiteAMida;

    MainViewModel mainViewModel;

    private TextView titulo, descripcion, explain;

    public VisitesAMidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.visites_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titulo = view.findViewById(R.id.text_visites);
        descripcion = view.findViewById(R.id.description_visites);
        explain = view.findViewById(R.id.visitesxxxxx);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        imageVisiteAMida = view.findViewById(R.id.imageVisitesaMida);

        mostrarVisitesaMide(view);

    }

    private void mostrarVisitesaMide(final View view) {
        mainViewModel.obtenerTourDetail("t9").observe(getViewLifecycleOwner(), new Observer<TourDetail>() {
            @Override
            public void onChanged(TourDetail tourDetail) {
                Log.e("ABCD", " lalacosa 1" + tourDetail.tourId );
                titulo.setText(tourDetail.tourName);
                Glide.with(requireActivity()).load(R.drawable.arctriomf).into(imageVisiteAMida);
                descripcion.setText(tourDetail.tourDescription);
                explain.setText(tourDetail.tourExplain);

                view.findViewById(R.id.meInteresaaMida).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                        Navigation.findNavController(view).navigate(R.id.reservarTourFragment);

                    }
                });
            }
        });
    }
}
