package com.example.bacelonatours;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
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
public class LoginFragment extends Fragment  {

    EditText emailLogin, password;
    Button iniciarSesionButton;
    private TextView phone, irAlRegistroTextView;;

    private AutenticacionViewModel autenticacionViewModel;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticacionViewModel = ViewModelProviders.of(requireActivity()).get(AutenticacionViewModel.class);


        emailLogin = view.findViewById(R.id.email_login);
        password = view.findViewById(R.id.password_login);



        irAlRegistroTextView = view.findViewById(R.id.iraregistro);
        iniciarSesionButton = view.findViewById(R.id.loginboton);
        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                autenticacionViewModel.iniciarSesion(emailLogin.getText().toString(), password.getText().toString());
            }
        });

        irAlRegistroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registrarseFragment);
            }
        });

        autenticacionViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(AutenticacionViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        Log.e("ABCD", " toy aqui Usuario Ade aqui me voy a.... " );
                        Navigation.findNavController(view).popBackStack(); //regresa patras

                        new AlertDialog.Builder(requireContext()).setTitle("\t\t           LOGIN IN      ")
                                .setMessage("\t      ")
                                .setMessage("\t                Estas dintre               ")
                                .setCancelable(true)
                                .create()
                                .show();
                        autenticacionViewModel.estadoDeLaAutenticacion.postValue((AutenticacionViewModel.EstadoDeLaAutenticacion.YA_AUTENTIFICADO));

                        break;

                                // YA AUTENTIFICADO es para si le das por segunda vez a LOGIN
                                // te detecta como YA ATENTIFICADO y entonces pues hace LOGIN OUT
                    case YA_AUTENTIFICADO:


                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

                        builder.setTitle("\t\t           LOGIN OUT      ")
                                .setMessage("\t      ")
                                .setMessage("\tAre you sure?")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        autenticacionViewModel.cerrarSesion();
                                        Navigation.findNavController(view).popBackStack();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Navigation.findNavController(view).popBackStack();
                                    }
                                })


                                .create()
                                .show();
                        break;

                    case AUTENTICACION_INVALIDA:
                        Toast.makeText(getContext(), "CREDENCIALES NO VALIDAS", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Log.e("ABCD", " toy aqui Usuario toy en getonBackPressed en Autentificacion " );
                        Navigation.findNavController(view).popBackStack(R.id.homeFragment, false);// modificat destino
                    }
                });
    }


}
