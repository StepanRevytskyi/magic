package com.arondillqs5328.magic.model.coin;

import com.arondillqs5328.magic.callback.coin.CoinCallback;
import com.arondillqs5328.magic.pojo.CoinListResponse;
import com.arondillqs5328.magic.retrofit.api.CoinAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinRepository implements Repository {

    private CoinAPI api;
    private CoinCallback callback;

    public CoinRepository(CoinAPI api) {
        this.api = api;
    }

    @Override
    public void setCallback(CoinCallback callback) {
        this.callback = callback;
    }

    @Override
    public void loadMore(int start, int limit) {
        Call<CoinListResponse> call = api.getCoinListResponse(start, limit);
        call.enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                if (response.body().getStatus().getErrorCode() == 0) {
                    callback.onSuccess(response.body().getCoinList());
                }
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                callback.onFailed();
            }
        });
    }
}
