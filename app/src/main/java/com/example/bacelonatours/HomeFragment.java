package com.example.bacelonatours;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private TextView moviditor, login;
    private ImageView imgHome;
    private AnimationDrawable frameAnimation;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.explorarTours).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tourListFragment);
            }
        });
        view.findViewById(R.id.movidito).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast1 =
                        Toast.makeText(requireActivity(),
                                "Por qué Tocas !", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,0);
                toast1.show();
            }
        });
        view.findViewById(R.id.loginHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.login);
            }
        });

                    //  SUBRAYAR TEXTO LOGIN
        login = view.findViewById(R.id.loginHome);
        SpannableString subrallarLogin = new SpannableString("Login");
        subrallarLogin.setSpan(new UnderlineSpan(), 0, subrallarLogin.length(), 0);
        login.setText(subrallarLogin);

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
        listText.add("  Barcelona Local Guide");
        listText.add("   Food And Wine Tours ");
        listText.add("        Barcelona ahoy    ");
        listText.add(" Discover Gaudi's Univers ");
        listText.add(" Live a cultural Inmmersion");
        listText.add("   Your Private Guide");

        marqueeView.startWithList(listText);




    }
}
