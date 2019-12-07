package com.example.bacelonatours;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
import android.widget.EditText;
import android.widget.TextView;

import com.example.bacelonatours.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarseFragment extends Fragment {

    NavController navController;
    MainViewModel mainViewModel;
    TextView noRegistrar, phone;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        emailEditText = view.findViewById(R.id.email_registrarse);
        passwordEditText = view.findViewById(R.id.password_registrarse);
        navController = Navigation.findNavController(view);


        if (mainViewModel.quiereRegistrarse ) { // VIENE DE DETAILFRAMENT "BOTON ME INTERESA"

            view.findViewById(R.id.noregistrarme).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Navigation.findNavController(view).navigate(R.id.contactoFragment);
                }
            });
            mainViewModel.quiereRegistrarse = false;
        }else{
            noRegistrar = view.findViewById(R.id.noregistrarme);
            noRegistrar.setVisibility(TextView.GONE);
        }
        view.findViewById(R.id.phoneRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = "667111222";
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }
            }
        });
        //  SUBRAYAR TEXTO phone
        phone = view.findViewById(R.id.phoneRegister);
        SpannableString subrallarPhone = new SpannableString("666 333 222");
        subrallarPhone.setSpan(new UnderlineSpan(), 0, subrallarPhone.length(), 0);
        phone.setText(subrallarPhone);

        view.findViewById(R.id.registerboton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rellenadoformulario = 0;
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                usuario = new Usuario(email, password);

                emailEditText.setError("Introdueix el teu email");
                passwordEditText.setError("Introdueix una contrasenya");

                if (!usuario.email.isEmpty()){  rellenadoformulario ++; }

                if (!usuario.password.isEmpty()){  rellenadoformulario ++; }

                if (rellenadoformulario == 2) {
                    mainViewModel.resgistrarUsuario(usuario.email, usuario.password);
                    mainViewModel.usuarioNoDisponible.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean existeUsuario) {
                            // aqui impremir que usuario disponible o no disponible
                            if (existeUsuario) {
                                Log.e("ABCD", " toy aqui condicional exiteUsuario " + usuario.email);
                                navController.navigate(R.id.verperfilfragment);
                            }

                        }
                    });

                    Log.e("ABCD", " toy aqui en verPerfil de vuelta " + usuario.email);
                }
            }
        });
    }
}
