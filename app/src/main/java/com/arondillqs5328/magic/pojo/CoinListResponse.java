package com.arondillqs5328.magic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinListResponse {

    @SerializedName("data")
    @Expose
    private List<Coin> coinList;

    @SerializedName("status")
    @Expose
    private Status status;

    public CoinListResponse() {
    }

    public CoinListResponse(List<Coin> coinList, Status status) {
        this.coinList = coinList;
        this.status = status;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
