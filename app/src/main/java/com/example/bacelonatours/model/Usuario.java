package com.example.bacelonatours.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public  int id;


    public String userName, password;

    public Usuario() {
    }

    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
