package com.example.bacelonatours.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bacelonatours.model.Usuario;

@Dao
public abstract class AppDao {

    @Insert
    public abstract void insertarUsuario(Usuario usuario);

    @Query(value = "SELECT * FROM Usuario WHERE email = :email")
    public abstract Usuario comprobarEmailDisponible(String email);

    @Query("SELECT * FROM Usuario WHERE email = :email AND password = :password")
    public abstract Usuario autenticar(String email, String password);



}
