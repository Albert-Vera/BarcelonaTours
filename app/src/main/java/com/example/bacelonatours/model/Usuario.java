package com.example.bacelonatours.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    public Usuario() {
    }

    public String email, password;



    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
