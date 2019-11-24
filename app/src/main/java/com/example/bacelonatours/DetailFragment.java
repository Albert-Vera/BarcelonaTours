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

    TextView titulo, descripcion, explain;
    ImageView imagenDetail;

    Tour tour;
//    Matrix matrix = new Matrix();
//    Float scale= 1f;
//    ScaleGestureDetector SGD;

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        titulo = view.findViewById(R.id.detail);
        descripcion = view.findViewById(R.id.tourResource);
        explain = view.findViewById(R.id.tourExplain);
        imagenDetail = view.findViewById(R.id.imageDetail);
        //SGD = new ScaleGestureDetector(requireActivity(), new ScaleListener());
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
                Log.e("ABCD", " lalacosa " + tourDetail.tourDescription);
                titulo.setText(tourDetail.tourName);
                Glide.with(requireActivity()).load(tourDetail.tourImage).into(imagenDetail);
                descripcion.setText(tourDetail.tourDescription);
                explain.setText(tourDetail.tourExplain);
            }
        });
    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scale = scale*detector.getScaleFactor();
//            scale = Math.max(0.1f,Math.min(scale,5f));
//            //matrix.setScale(scale,scale);
//            imagenDetail.setImageMatrix(matrix);
//            return true;
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        SGD.onTouchEvent(event);
//        return true;
//    }
}
