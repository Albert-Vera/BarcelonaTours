package com.example.bacelonatours.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bacelonatours.model.Usuario;

@Dao
public abstract class AppDao {

    @Query(value = "SELECT * FROM Usuario WHERE email = :email")
    public abstract Usuario comprobarEmailDisponible(String email);




    @Insert
    public abstract void insertarUsuario(Usuario usuario);

//    @Query(value = "SELECT * FROM Usuario WHERE id = MAX(id)")
//    public abstract Usuario ultimoRegistro();

}
