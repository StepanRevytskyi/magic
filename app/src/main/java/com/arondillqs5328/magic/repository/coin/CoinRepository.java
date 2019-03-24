package com.arondillqs5328.magic.repository.coin;

import com.arondillqs5328.magic.callback.coin.CoinCallback;
import com.arondillqs5328.magic.model.pojo.CoinListResponse;
import com.arondillqs5328.magic.retrofit.api.CoinAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinRepository implements Repository {

    private CoinAPI api;
    private CoinCallback callback;

    public CoinRepository(CoinAPI api, CoinCallback callback) {
        this.api = api;
        this.callback = callback;
    }

    @Override
    public void loadMore(int start, int limit) {
        Call<CoinListResponse> call = api.getCoins(start, limit);
        call.enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                if (response.body().getStatus().getError_code() == 0) {
                    callback.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                callback.onFailed();
            }
        });
    }
}
