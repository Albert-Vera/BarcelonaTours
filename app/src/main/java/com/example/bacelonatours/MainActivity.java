package com.example.bacelonatours;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.tourListFragment, R.id.detailFragment,
                R.id.loginFragment, R.id.contactoFragment, R.id.registrarseFragment, R.id.verperfilfragment,
                R.id.rutas_a_midaFragment, R.id.gastronomiaFragment )
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.francesettings:
                new AlertDialog.Builder(this).setTitle("\t\tAhora, si le pones imaginación ")
                        .setMessage("\t      ")
                        .setMessage("\t     Lo verás todo en Francés ")
                        .setCancelable(true)
                        .create()
                        .show();
                break;
            case R.id.englishsettings:
                new AlertDialog.Builder(this).setTitle("\t\tAhora, si le pones imaginación ")
                        .setMessage("\t      ")
                        .setMessage("\t     Lo verás todo en Inglés ")
                        .setCancelable(true)
                        .create()
                        .show();
                break;
            case R.id.catalasettings:
                new AlertDialog.Builder(this).setTitle("\t\tAhora, si le pones imaginación ")
                        .setMessage("\t      ")
                        .setMessage("\t     Lo verás todo en Catalán ")
                        .setCancelable(true)
                        .create()
                        .show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }
}
