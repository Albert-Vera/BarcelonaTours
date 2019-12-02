package com.example.bacelonatours;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bacelonatours.db.AppDao;
import com.example.bacelonatours.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerPerfilFragment extends Fragment {

    TextView usuarioText;
    MainViewModel mainViewModel;
    Usuario usuario ;


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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usuarioText = view.findViewById(R.id.verperfil);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        usuario = new Usuario();

  //      mainViewModel.leerUltimoRegistro();

        mainViewModel.usuario.observe(getViewLifecycleOwner(), new Observer<Usuario>() {

            @Override
            public void onChanged(Usuario usuario) {
                Log.e("ABCD", " toy aqui en verPerfil no entra ni en broma " + usuario.email);
                mostrarDetallePerfil(usuario);
            }
        });


    }

    public void mostrarDetallePerfil(Usuario usuario){

        Log.e("ABCD", " toy aqui en verPerfil mostar detalle  " + usuario.email);
        usuarioText.setText("registro completado");
    }
}
