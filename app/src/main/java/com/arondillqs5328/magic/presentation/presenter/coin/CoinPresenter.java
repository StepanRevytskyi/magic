package com.arondillqs5328.magic.presentation.presenter.coin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arondillqs5328.magic.callback.coin.CoinCallback;
import com.arondillqs5328.magic.model.coin.CoinRepository;
import com.arondillqs5328.magic.pojo.Coin;
import com.arondillqs5328.magic.presentation.view.coin.CoinView;
import com.arondillqs5328.magic.retrofit.RetrofitClient;
import com.arondillqs5328.magic.retrofit.api.CoinAPI;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class CoinPresenter extends MvpPresenter<CoinView> implements CoinCallback {

    private boolean isLoading = false;
    private Integer start = 1;
    private Integer limit = 25;
    private MutableLiveData<List<Coin>> coinLiveData = new MutableLiveData<>();
    private CoinRepository repository;

    public CoinPresenter() {
        repository = new CoinRepository(new RetrofitClient().getRetrofitInstance().create(CoinAPI.class));
        repository.setCallback(this);

        List<Coin> coins = new ArrayList<>();
        coinLiveData.setValue(coins);
    }

    public LiveData<List<Coin>> getCoinLiveData() {
        return coinLiveData;
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onSuccess(List<Coin> coins) {
        List<Coin> oldCoins = getCoinLiveData().getValue();
        oldCoins.addAll(coins);
        coinLiveData.setValue(oldCoins);

        isLoading = false;
        getViewState().hideProgressBar();
        getViewState().hideFooter();

        Log.i("TAG_L", "size = " + getCoinLiveData().getValue().size());
    }

    public void onLoadMore() {
        if (getCoinLiveData().getValue().size() == 0) {
            if (!isLoading) {
                getViewState().showProgressBar();

                repository.loadMore(start, limit);
                start = start + limit;
                isLoading = true;
            }
        } else {
            if (!isLoading) {
                getViewState().showFooter();

                repository.loadMore(start, limit);
                start = start + limit;
                isLoading = true;
            }
        }
    }
}
