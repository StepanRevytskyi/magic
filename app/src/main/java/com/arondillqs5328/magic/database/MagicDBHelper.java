package com.arondillqs5328.magic.database;

import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.arondillqs5328.magic.CoinApplication;
import com.arondillqs5328.magic.pojo.Coin;

import java.util.Date;
import java.util.List;

public class MagicDBHelper {

    public MagicDBHelper() {
    }

    public LiveData<List<Integer>> getCoin() {
        return CoinApplication.getInstance().getDatabase().coinDao().getCoin();
    }

    public void insertCoinIntoDB(Coin coin) {
        new InsertTask().execute(coin);
    }

    public void deleteCoinFromDB(int id) {
        new DeleteTask().execute(id);
    }


    private static class InsertTask extends AsyncTask<Coin, Void, Void> {

        @Override
        protected Void doInBackground(Coin... coins) {
            CoinApplication.getInstance().getDatabase().coinDao().insertCoin(coins[0]);
            Log.i("TAG_S", String.valueOf(new Date().getTime()));
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            CoinApplication.getInstance().getDatabase().coinDao().deleteCoinById(integers[0]);
            return null;
        }
    }

}
