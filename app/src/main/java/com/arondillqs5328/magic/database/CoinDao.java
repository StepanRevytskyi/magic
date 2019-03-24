package com.arondillqs5328.magic.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arondillqs5328.magic.model.pojo.Coin;

import java.util.List;

@Dao
public interface CoinDao {

    @Query("SELECT id FROM coins")
    LiveData<List<Integer>> getCoin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCoin(Coin coin);

    @Query("DELETE FROM coins WHERE id = :id")
    void deleteCoinById(int id);
}
