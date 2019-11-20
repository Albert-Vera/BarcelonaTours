package com.example.bacelonatours;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bacelonatours.api.BarceloninaApiModule;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.Tour;
import com.example.bacelonatours.model.TourDetail;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    Application application;


    public MutableLiveData<Tour> tour = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
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