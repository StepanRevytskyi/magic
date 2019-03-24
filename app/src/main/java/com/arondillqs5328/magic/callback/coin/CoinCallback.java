package com.arondillqs5328.magic.callback.coin;

import com.arondillqs5328.magic.model.pojo.Coin;

import java.util.List;

public interface CoinCallback {

    void onSuccess(List<Coin> coins);

    void onFailed();

}
