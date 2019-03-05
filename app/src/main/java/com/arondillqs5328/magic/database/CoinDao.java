package com.arondillqs5328.magic.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.arondillqs5328.magic.pojo.Coin;

@Dao
public interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCoin(Coin coin);

    @Query("DELETE FROM coins WHERE id = :id")
    void deleteCoinById(int id);
}
