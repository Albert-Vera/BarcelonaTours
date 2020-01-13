package com.example.bacelonatours;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacelonatours.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarseFragment extends Fragment {

    private AutenticacionViewModel autenticacionViewModel;
    NavController navController;
    MainViewModel mainViewModel;
    TextView noRegistrar;
    private Button registrarButton;
    Usuario usuario;
    EditText emailEditText, passwordEditText;

    public RegistrarseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrarse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //navController = Navigation.findNavController(view);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        emailEditText = view.findViewById(R.id.email_registrarse);
        passwordEditText = view.findViewById(R.id.password_registrarse);
        registrarButton = view.findViewById(R.id.registerboton);
       // navController = Navigation.findNavController(view);


        if (mainViewModel.quiereRegistrarse ) { // VIENE DE DETAILFRAMENT "BOTON ME INTERESA"

            view.findViewById(R.id.noregistrarme).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Navigation.findNavController(view).navigate(R.id.reservarTourFragment);
                }
            });
            mainViewModel.quiereRegistrarse = false;
        }else{
            noRegistrar = view.findViewById(R.id.noregistrarme);
            noRegistrar.setVisibility(TextView.GONE);
        }


        autenticacionViewModel = ViewModelProviders.of(requireActivity()).get(AutenticacionViewModel.class);

        // Poner el estado del registro al estado INICIAR,
        // (por si se habia quedado en COMPLETADO o NOMBRE_NO_DISPONIBLE)
        autenticacionViewModel.iniciarRegistro();

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticacionViewModel.crearCuentaEIniciarSesion(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        autenticacionViewModel.estadoDelRegistro.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDelRegistro>() {
            @Override
            public void onChanged(AutenticacionViewModel.EstadoDelRegistro estadoDelRegistro) {
                switch (estadoDelRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                        Toast.makeText(getContext(), "NOMBRE DE USUARIO NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        autenticacionViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(AutenticacionViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        Log.e("ABCD", " toy aqui Usuario toy en Autentificado 231.. " );
                        //Navigation.findNavController(view).popBackStack();
                        break;
                }
            }
        });

    }
}
