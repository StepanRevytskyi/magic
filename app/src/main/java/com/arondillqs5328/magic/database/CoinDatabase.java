package com.arondillqs5328.magic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arondillqs5328.magic.model.pojo.Coin;

@Database(entities = {Coin.class}, version = 1, exportSchema = false)
public abstract class CoinDatabase extends RoomDatabase {

    public static final String DB_NAME = "coin.db";

    public abstract CoinDao coinDao();

}
