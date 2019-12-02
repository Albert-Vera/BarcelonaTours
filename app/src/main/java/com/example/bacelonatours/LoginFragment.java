package com.example.bacelonatours;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bacelonatours.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment  {

    EditText emailLogin, password;
    MainViewModel mainViewModel;
    Usuario usuario;
    NavController navController;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        emailLogin = view.findViewById(R.id.email_login);
        password = view.findViewById(R.id.password_login);


        view.findViewById(R.id.loginboton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rellenadoformulario = 0;
                usuario = new Usuario(emailLogin.getText().toString(), password.getText().toString());
                Log.e("ABCD", " toy aqui en Login.empty " + usuario.email);
                emailLogin.setError("Introdueix el teu email");
                password.setError("Introdueix una contrasenya");

                if (!usuario.email.isEmpty()){  rellenadoformulario ++; }

                if (!usuario.password.isEmpty()){  rellenadoformulario ++; }

                if (rellenadoformulario == 2){
                    mainViewModel.resgistrarUsuario(usuario.email, usuario.password);
                    navController.navigate(R.id.verperfilfragment);

                }
            }
        });

        view.findViewById(R.id.iraregistro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.registrarse);


            }
        });


    }

}
