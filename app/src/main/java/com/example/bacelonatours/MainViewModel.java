package com.example.bacelonatours;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bacelonatours.api.BarceloninaApiModule;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.TourDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    public String tourId;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BarceloninaResponse> obtenerTours(){
        final MutableLiveData<BarceloninaResponse> apiResponse = new MutableLiveData<>();

        BarceloninaApiModule.barceloninaApi.obtenerTours().enqueue(new Callback<BarceloninaResponse>() {
            @Override
            public void onResponse(Call<BarceloninaResponse> call, Response<BarceloninaResponse> response) {
                apiResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BarceloninaResponse> call, Throwable t) {
            }
        });

        return apiResponse;
    }

    public LiveData<TourDetail> obtenerTour(String id){
        final MutableLiveData<TourDetail> tourDetailMutableLiveData = new MutableLiveData<>();

        BarceloninaApiModule.barceloninaApi.obtenerTour(id).enqueue(new Callback<TourDetail>() {
            @Override
            public void onResponse(Call<TourDetail> call, Response<TourDetail> response) {
                tourDetailMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TourDetail> call, Throwable t) {
            }
        });

        return tourDetailMutableLiveData;
    }
}