package com.example.bacelonatours.api;


import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.TourDetail;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BarceloninaApi {
    @GET("bd.json")
    Call<BarceloninaResponse> obtenerTours();

    @GET("{id}")
    Call<TourDetail> obtenerTour(String id);
}
