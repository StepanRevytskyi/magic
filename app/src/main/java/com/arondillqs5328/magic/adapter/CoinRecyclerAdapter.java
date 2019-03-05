package com.arondillqs5328.magic.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinRecyclerAdapter extends RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder> {

    private List<Coin> coins;
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

        coinViewHolder.checkBox.setChecked(selectedCoins.get(i, false));

        coinViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    magicDBHelper.insertCoinIntoDB(coins.get(coinViewHolder.getAdapterPosition()));
//                } else {
//                    magicDBHelper.deleteCoinFromDB(coins.get(coinViewHolder.getAdapterPosition()).getId());
//                }
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

    @Override
    public int getItemCount() {
        return coins.size();
    }


    class CoinViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.coin_logo) ImageView logoImageView;
        @BindView(R.id.coin_label) TextView labelTextView;
        @BindView(R.id.checkbox) CheckBox checkBox;

        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
