package com.arondillqs5328.magic;

import android.app.Application;
import androidx.room.Room;

import com.arondillqs5328.magic.database.CoinDatabase;

public class CoinApplication extends Application {

    private static CoinApplication instance;
    private CoinDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, CoinDatabase.class, CoinDatabase.DB_NAME)
                .build();
    }

    public static CoinApplication getInstance() {
        return instance;
    }

    public CoinDatabase getDatabase() {
        return database;
    }
}
