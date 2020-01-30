package com.example.bacelonatours;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerPerfilFragment extends Fragment {


    private AutenticacionViewModel autenticacionViewModel;
    private Button buttonContinuar, buttonCerrarSesion;
    private TextView textoLogeadoOk, usuarioText;


    public VerPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        autenticacionViewModel = ViewModelProviders.of(requireActivity()).get(AutenticacionViewModel.class);


        textoLogeadoOk = view.findViewById(R.id.textoLogeador);
        view.findViewById(R.id.continuarLogeao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homeFragment);
            }
        });
        view.findViewById(R.id.cerrarSessioButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autenticacionViewModel.cerrarSesion();
                mostrarCerrarSesion();
                Navigation.findNavController(view).navigate(R.id.homeFragment);
            }
        });


    }

    private void mostrarCerrarSesion() {
        new AlertDialog.Builder(requireContext()).setTitle("\n\t\t                Sessió Tancada ")
                .setMessage("\n\n")
                .setCancelable(true)
                .create()
                .show();

    }

}
