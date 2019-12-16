package com.example.bacelonatours;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bacelonatours.db.AppDao;
import com.example.bacelonatours.db.AppDataBase;
import com.example.bacelonatours.model.Usuario;

/**
 * View Model para registro de usuarios y Login
 */

public class AutenticacionViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> usuarioActivo= new MutableLiveData<>();

    public enum EstadoDeLaAutenticacion {
        NO_AUTENTICADO,
        AUTENTICADO,
        AUTENTICACION_INVALIDA
    }

    public enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO
    }

    private AppDao appDao;

    public Usuario usuarioLogeado;

    public MutableLiveData<EstadoDeLaAutenticacion> estadoDeLaAutenticacion = new MutableLiveData<>(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    public MutableLiveData<Boolean> usuarioActivado= new MutableLiveData<>();

    public AutenticacionViewModel(@NonNull Application application) {
        super(application);
        appDao = AppDataBase.getInstance(application).appDao();
    }

    public void iniciarRegistro(){
        estadoDelRegistro.postValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    }

    public void crearCuentaEIniciarSesion(final String email, final String password) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario = appDao.comprobarEmailDisponible(email);
                if(usuario == null){
                    appDao.insertarUsuario(new Usuario(email, password));
                    estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                    iniciarSesion(email, password);
                } else {
                    estadoDelRegistro.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }
            }
        });
    }

    public void iniciarSesion(final String nombre, final String contrasenya) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario = appDao.autenticar(nombre, contrasenya);
                if(usuario != null){
                    usuarioLogeado = usuario;
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICADO);
                    usuarioActivo.postValue(true);
                } else {
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICACION_INVALIDA);
                }
            }
        });
    }

    public void cerrarSesion() {
        usuarioLogeado = null;
        estadoDeLaAutenticacion.setValue(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    }


}