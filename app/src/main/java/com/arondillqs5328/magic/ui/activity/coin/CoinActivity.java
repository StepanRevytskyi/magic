package com.arondillqs5328.magic.ui.activity.coin;

import android.os.Bundle;

import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.ui.fragment.coin.CoinFragment;

import androidx.appcompat.app.AppCompatActivity;

public class CoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.coin_frame_layout, CoinFragment.getInstance())
                    .commit();
        }
    }
}
