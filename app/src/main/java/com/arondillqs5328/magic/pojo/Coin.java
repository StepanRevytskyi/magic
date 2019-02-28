package com.arondillqs5328.magic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("is_active")
    @Expose
    private int isActive;

    @SerializedName("first_historical_data")
    @Expose
    private String firstHistoricalData;

    @SerializedName("last_historical_data")
    @Expose
    private String lastHistoricalData;

    @SerializedName("platform")
    @Expose
    private Object platform;

    public Coin() {
    }

    public Coin(int id, String name, String symbol, String slug, int isActive,
                String firstHistoricalData, String lastHistoricalData, Object platform) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.isActive = isActive;
        this.firstHistoricalData = firstHistoricalData;
        this.lastHistoricalData = lastHistoricalData;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getFirstHistoricalData() {
        return firstHistoricalData;
    }

    public void setFirstHistoricalData(String firstHistoricalData) {
        this.firstHistoricalData = firstHistoricalData;
    }

    public String getLastHistoricalData() {
        return lastHistoricalData;
    }

    public void setLastHistoricalData(String lastHistoricalData) {
        this.lastHistoricalData = lastHistoricalData;
    }

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }
}
