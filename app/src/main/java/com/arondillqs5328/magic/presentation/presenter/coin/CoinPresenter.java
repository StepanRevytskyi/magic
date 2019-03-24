package com.arondillqs5328.magic.presentation.presenter.coin;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arondillqs5328.magic.callback.coin.CoinCallback;
import com.arondillqs5328.magic.model.pojo.Coin;
import com.arondillqs5328.magic.presentation.view.coin.CoinView;
import com.arondillqs5328.magic.repository.coin.CoinRepository;
import com.arondillqs5328.magic.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@InjectViewState
public class CoinPresenter extends MvpPresenter<CoinView> implements CoinCallback {

    private boolean isLoading = false;
    private Integer start = 1;
    private Integer limit = 20;
    private MutableLiveData<List<Coin>> coinsLiveData = new MutableLiveData<>();
    private CoinRepository repository;

    public CoinPresenter() {
        repository = new CoinRepository(new RetrofitClient().createAPI(), this);

        List<Coin> coins = new ArrayList<>();
        coinsLiveData.setValue(coins);
    }

    public LiveData<List<Coin>> getCoinsLiveData() {
        return coinsLiveData;
    }

    public void onLoadMore(int position, int count) {
        if (position == count - 1) {
            if (getCoinsLiveData().getValue().size() == 0) {
                if (!isLoading) {
                    getViewState().showProgressBar();
                    repository.loadMore(start, limit);
                    updateParams();
                }
            } else {
                if (!isLoading) {
                    repository.loadMore(start, limit);
                    updateParams();
                }
            }
        }
    }

    private void updateParams() {
        start = start + limit;
        isLoading = true;
    }

    @Override
    public void onSuccess(List<Coin> coins) {
        List<Coin> oldCoins = getCoinsLiveData().getValue();
        oldCoins.addAll(coins);
        coinsLiveData.setValue(oldCoins);

        isLoading = false;

        getViewState().hideProgressBar();
    }

    @Override
    public void onFailed() {

    }
}
