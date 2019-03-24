package com.arondillqs5328.magic.ui.activity.coin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.ui.fragment.coin.CoinFragment;

public class CoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, CoinFragment.getInstance())
                    .commit();
        }
    }
}
