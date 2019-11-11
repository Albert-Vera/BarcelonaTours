package com.example.bacelonatours.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BarceloninaApiModule {

    public static BarceloninaApi barceloninaApi = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Albertvedu/BacelonaTours/master/app/src/main/res/raw/")
            .client(new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();

                            long t1 = System.nanoTime();
                            Log.e("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
                                    request.url(), chain.connection(), request.headers()));

                            okhttp3.Response response = chain.proceed(request);

                            long t2 = System.nanoTime();
                            Log.e("INTERCEPTOR---", String.format("Received response for %s in %.1fms%n%s",
                                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                            return response;
                        }
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BarceloninaApi.class);

}

