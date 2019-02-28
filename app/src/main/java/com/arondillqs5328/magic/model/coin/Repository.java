package com.arondillqs5328.magic.model.coin;

import com.arondillqs5328.magic.callback.coin.CoinCallback;

public interface Repository {

    void setCallback(CoinCallback callback);

    void loadMore(int start, int limit);

}
