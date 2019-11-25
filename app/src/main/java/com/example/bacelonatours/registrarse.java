package com.example.bacelonatours;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class registrarse extends Fragment {

    NavController navController;
    MainViewModel mainViewModel;

    EditText emaileditText, passwordEditText;
    public registrarse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrarse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class));
        emaileditText = view.findViewById(R.id.userName);
        passwordEditText = view.findViewById(R.id.password);

        view.findViewById(R.id.registerboton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emaileditText.getText().toString();
                String password = passwordEditText.getText().toString();
                mainViewModel.resgistrarUsuario(email, password);
                mainViewModel.usuarioNoDisponible.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aqui impremir que usuario no disponible

                    }
                });

            }
        });
    }
}
