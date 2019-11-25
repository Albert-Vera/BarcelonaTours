package com.example.bacelonatours.model;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

public abstract class AppDao {

    @Query("SELECT * FROM Usuario WHERE email = :email)")
    abstract Usuario comprobarEmailDisponible(String email);


    @Insert
    public abstract void insertarUsuario(Database database);
        ;
    }
}
