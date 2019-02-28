package com.arondillqs5328.magic.retrofit.api;

import com.arondillqs5328.magic.pojo.CoinListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinAPI {

    @GET("/v1/cryptocurrency/map")
    Call<CoinListResponse> getCoinListResponse(@Query("start") Integer start,
                                               @Query("limit") Integer limit);

}
