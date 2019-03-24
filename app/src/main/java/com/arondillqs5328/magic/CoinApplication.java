package com.arondillqs5328.magic;

import android.app.Application;

import com.arondillqs5328.magic.database.CoinDatabase;

import androidx.room.Room;

public class CoinApplication extends Application {

    private static CoinApplication instance;
    private CoinDatabase database;
    private final Object block = new Object();

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
        synchronized (block) {
            return database;
        }
    }
}
