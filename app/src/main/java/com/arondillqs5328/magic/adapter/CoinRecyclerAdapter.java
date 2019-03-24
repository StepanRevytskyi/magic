package com.arondillqs5328.magic.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.database.MagicDBHelper;
import com.arondillqs5328.magic.pojo.Coin;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


public class CoinRecyclerAdapter extends RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder> {

    private List<Coin> coins;
    private List<Integer> favoriteCoins;

    private final SparseBooleanArray selectedCoins = new SparseBooleanArray();
    private MagicDBHelper magicDBHelper;

    private final String IMAGE_URL = "https://s2.coinmarketcap.com/static/img/coins/128x128/";

    public CoinRecyclerAdapter(List<Coin> coins, MagicDBHelper magicDBHelper) {
        this.coins = coins;
        this.magicDBHelper = magicDBHelper;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coin, viewGroup, false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CoinViewHolder coinViewHolder, int i) {
        String url = IMAGE_URL + coins.get(i).getId() + ".png";
        coinViewHolder.labelTextView.setText(coins.get(i).getName());

        coinViewHolder.favoriteCheckBox.setChecked(selectedCoins.get(i, false));

        if (favoriteCoins.contains(coins.get(i).getId())) {
            coinViewHolder.favoriteCheckBox.setChecked(true);
        }

        coinViewHolder.favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!favoriteCoins.contains(coins.get(coinViewHolder.getAdapterPosition()).getId())) {
                        Log.i("TAG_L", String.valueOf(new Date().getTime()));
                        magicDBHelper.insertCoinIntoDB(coins.get(coinViewHolder.getAdapterPosition()));

                    }
                } else {
                    if (favoriteCoins.contains(coins.get(coinViewHolder.getAdapterPosition()).getId())) {
                        magicDBHelper.deleteCoinFromDB(coins.get(coinViewHolder.getAdapterPosition()).getId());
                    }
                }
                selectedCoins.put(coinViewHolder.getAdapterPosition(), isChecked);
            }
        });

        Picasso.get()
                .load(url)
                .error(R.drawable.ic_terrain_black_24dp)
                .into(coinViewHolder.logoImageView);
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public void setFavoriteCoins(List<Integer> favoriteCoins) {
        this.favoriteCoins = favoriteCoins;
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    class CoinViewHolder extends RecyclerView.ViewHolder {

        private ImageView logoImageView;
        private TextView labelTextView;
        private CheckBox favoriteCheckBox;

        CoinViewHolder(@NonNull View itemView) {
            super(itemView);

            logoImageView = itemView.findViewById(R.id.coin_logo);
            labelTextView = itemView.findViewById(R.id.coin_label);
            favoriteCheckBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
