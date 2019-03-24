package com.arondillqs5328.magic.retrofit;

import com.arondillqs5328.magic.retrofit.api.CoinAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final String API_URL = "https://pro-api.coinmarketcap.com";
    private final String API_HEADER = "X-CMC_PRO_API_KEY";
    private final String API_KEY = "63405db2-b682-4c22-8af7-255b21e3f09d";

    public CoinAPI createAPI() {
        return getRetrofit().create(CoinAPI.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build();
    }

    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader(API_HEADER, API_KEY)
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
