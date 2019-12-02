package com.example.bacelonatours.db;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bacelonatours.model.Usuario;

import java.util.concurrent.Callable;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDataBase extends androidx.room.RoomDatabase{

    public abstract AppDao appDao();

    static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context){

        if(appDataBase == null){
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, "app.db")
                     .fallbackToDestructiveMigration()
                     .addCallback(new RoomDatabase.Callback() {
                         @Override
                         public void onCreate(@NonNull SupportSQLiteDatabase db) {
                             super.onCreate(db);
                             crearUsuario();
                         }

                     }) // anadir por defecto datos
                    .build();
        }
        return appDataBase;
    }

    private static void crearUsuario(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
               appDataBase.appDao().insertarUsuario(new Usuario("admin","admin"));
            }
        });
    }

//    private static void leerUsuario(){
//        AsyncTask.execute(new Callable<Usuario>())
//        });
//    }
}
