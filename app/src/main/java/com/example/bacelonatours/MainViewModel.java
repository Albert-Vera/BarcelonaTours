package com.example.bacelonatours;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bacelonatours.db.AppDao;
import com.example.bacelonatours.db.AppDataBase;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.Tour;
import com.example.bacelonatours.model.TourDetail;
import com.example.bacelonatours.model.Usuario;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class MainViewModel extends AndroidViewModel {

    Application application;
    AppDao appDao;

    public MutableLiveData<Tour> tourSeleccionado = new MutableLiveData<>();
    public MutableLiveData<Usuario> usuario = new MutableLiveData<>();
    public Boolean quiereRegistrarse = false;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;

        appDao = AppDataBase.getInstance(application).appDao();
    }

    public LiveData<BarceloninaResponse> obtenerTours(){
        final MutableLiveData<BarceloninaResponse> apiResponse = new MutableLiveData<>();

        try {
            Gson gson = new Gson();
            InputStream raw =  application.getResources().openRawResource(R.raw.bd);
            Reader rd = new BufferedReader(new InputStreamReader(raw));
            BarceloninaResponse data = gson.fromJson(rd, BarceloninaResponse.class);

            apiResponse.setValue(data);

        }catch (Exception e){
            Log.e("ABCD", "Exception" + e.getMessage());
        }

        return apiResponse;
    }

    public LiveData<BarceloninaResponse> obtenerGalleria(){
        final MutableLiveData<BarceloninaResponse> apiResponse = new MutableLiveData<>();

        try {
            Gson gson = new Gson();
            InputStream raw =  application.getResources().openRawResource(R.raw.galeria);
            Reader rd = new BufferedReader(new InputStreamReader(raw));
            BarceloninaResponse data = gson.fromJson(rd, BarceloninaResponse.class);

            apiResponse.setValue(data);

        }catch (Exception e){
            Log.e("ABCD", "Exception" + e.getMessage());
        }

        return apiResponse;
    }


    public LiveData<TourDetail> obtenerTourDetail(String tourId){
        final MutableLiveData<TourDetail> apiResponse = new MutableLiveData<>();

        try {
            Gson gson = new Gson();

            int resourceId = application.getResources().getIdentifier(tourId, "raw", "com.example.bacelonatours");

            InputStream raw =  application.getResources().openRawResource(resourceId);
            Reader rd = new BufferedReader(new InputStreamReader(raw));
            TourDetail data = gson.fromJson(rd, TourDetail.class);

            apiResponse.setValue(data);

        }catch (Exception e){
            Log.e("ABCD", "Exception" + e.getMessage());
        }

        return apiResponse;
    }

//    public LiveData<BarceloninaResponse> obtenerTours(){
//        final MutableLiveData<BarceloninaResponse> apiResponse = new MutableLiveData<>();
//
//        BarceloninaApiModule.barceloninaApi.obtenerTours().enqueue(new Callback<BarceloninaResponse>() {
//            @Override
//            public void onResponse(Call<BarceloninaResponse> call, Response<BarceloninaResponse> response) {
//                apiResponse.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<BarceloninaResponse> call, Throwable t) {
//            }
//        });
//
//        return apiResponse;
//    }
//
//    public LiveData<TourDetail> obtenerTour(String id){
//        final MutableLiveData<TourDetail> tourDetailMutableLiveData = new MutableLiveData<>();
//
//        BarceloninaApiModule.barceloninaApi.obtenerTour(id).enqueue(new Callback<TourDetail>() {
//            @Override
//            public void onResponse(Call<TourDetail> call, Response<TourDetail> response) {
//                tourDetailMutableLiveData.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<TourDetail> call, Throwable t) {
//            }
//        });
//
//        return tourDetailMutableLiveData;
//    }
}