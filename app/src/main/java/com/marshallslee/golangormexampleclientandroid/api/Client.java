package com.marshallslee.golangormexampleclientandroid.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private Retrofit retrofit;

    public Client(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public API getApi() {
        return retrofit.create(API.class);
    }
}
