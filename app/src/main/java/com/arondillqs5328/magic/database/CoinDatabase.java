package com.arondillqs5328.magic.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.arondillqs5328.magic.pojo.Coin;

@Database(entities = {Coin.class}, version = 1, exportSchema = false)
public abstract class CoinDatabase extends RoomDatabase {

    public static final String DB_NAME = "coin.db";

    public abstract CoinDao coinDao();

}
