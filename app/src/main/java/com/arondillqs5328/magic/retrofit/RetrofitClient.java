package com.arondillqs5328.magic.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final String API_URL = "https://pro-api.coinmarketcap.com";

    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .client(new OkHttpClient().getOkHttpClient())
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
