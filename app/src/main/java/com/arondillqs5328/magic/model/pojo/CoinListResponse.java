package com.arondillqs5328.magic.model.pojo;

import java.util.List;

public class CoinListResponse {

    private List<Coin> data;
    private Status status;

    public CoinListResponse() {

    }

    public CoinListResponse(List<Coin> data, Status status) {
        this.data = data;
        this.status = status;
    }

    public List<Coin> getData() {
        return data;
    }

    public void setData(List<Coin> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
