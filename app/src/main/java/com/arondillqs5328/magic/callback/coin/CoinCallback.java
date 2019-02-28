package com.arondillqs5328.magic.callback.coin;

import com.arondillqs5328.magic.pojo.Coin;

import java.util.List;

public interface CoinCallback {

    void onFailed();

    void onSuccess(List<Coin> coins);
}
