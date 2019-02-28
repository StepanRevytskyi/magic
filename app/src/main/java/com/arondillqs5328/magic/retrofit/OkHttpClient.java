package com.arondillqs5328.magic.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClient {

    private final String API_HEADER = "X-CMC_PRO_API_KEY";
    private final String API_KEY = "63405db2-b682-4c22-8af7-255b21e3f09d";

    public okhttp3.OkHttpClient getOkHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
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
