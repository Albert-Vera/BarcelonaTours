package com.example.bacelonatours;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Fragment de inicio
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private TextView moviditor;
    private ImageView imgHome;
    private AnimationDrawable frameAnimation;
    AutenticacionViewModel autenticacionViewModel ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.explorarTours).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tourListFragment);
            }
        });
        view.findViewById(R.id.movidito);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast toast1 =
                        Toast.makeText(requireActivity(),
                                "Por qué Tocas !", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER | Gravity.LEFT, 250, 0);
                toast1.show();
                return false;
            }

//            @Override
//            public void onClick(View view) {
//                Toast toast1 =
//                        Toast.makeText(requireActivity(),
//                                "Por qué Tocas !", Toast.LENGTH_SHORT);
//                toast1.setGravity(Gravity.CENTER | Gravity.LEFT, 250, 0);
//                toast1.show();
//
//            }
        });


//        autenticacionViewModel = ViewModelProviders.of(requireActivity()).get(AutenticacionViewModel.class);
//        autenticacionViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDeLaAutenticacion>() {
//            @Override
//            public void onChanged(AutenticacionViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
//                switch (estadoDeLaAutenticacion){
//                    case AUTENTICADO:
//                        mostrarLoginEnHome(view, "Login Out");
//
//                        break;
//                    case NO_AUTENTICADO:
//                        mostrarLoginEnHome(view, "Login");
//                    default:
//                        mostrarLoginEnHome(view, "Login");
//                        break;
//                }
//            }
//        });



        // TEXTO DE LA HOME QUE VA CORRIENDO
        moviditor = view.findViewById(R.id.movidito);
        moviditor.setSelected(true);

        // IMAGENES DE LA HOME ALTERNAS
        imgHome = (ImageView) view.findViewById(R.id.fotoPortada);
        imgHome.setImageResource(0);
        // set the xml with images
        imgHome.setBackgroundResource(R.drawable.animation);
        // get the background to show the animation
        frameAnimation = (AnimationDrawable) imgHome.getBackground();
        frameAnimation.start();



        // TEXTO DE LA IMAGENHOME CON MOVIMIENTO
        MarqueeView marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);
        List<String> listText = new ArrayList<>();
        listText.add("          Visites Guiades       ");
        listText.add("           Gastronomia          ");
        listText.add("          Immersió cultural      ");
        listText.add("Descobreix l'univers Gaudi ");
        listText.add("         Tallers artesanals     ");
        listText.add("       Guia Privat a Barcelona  ");

        marqueeView.startWithList(listText);
    }

}
