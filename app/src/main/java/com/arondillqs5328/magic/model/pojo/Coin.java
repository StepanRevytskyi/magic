package com.arondillqs5328.magic.model.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coins")
public class Coin {

    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    public Coin() {

    }

    public Coin(int id, String name) {
        this.id = id;
        this.name = name;
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
}
