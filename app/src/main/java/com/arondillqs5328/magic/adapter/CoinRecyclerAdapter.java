package com.arondillqs5328.magic.adapter;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.pojo.Coin;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinRecyclerAdapter extends RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder> {

    private LiveData<List<Coin>> coinLiveData;
    private final String IMAGE_URL = "https://s2.coinmarketcap.com/static/img/coins/128x128/";

    public CoinRecyclerAdapter(LiveData<List<Coin>> coinLiveData) {
        this.coinLiveData = coinLiveData;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coin, viewGroup, false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder coinViewHolder, int i) {
        String url = IMAGE_URL + coinLiveData.getValue().get(i).getId() + ".png";
        coinViewHolder.labelTextView.setText(coinLiveData.getValue().get(i).getName());

        Picasso.get()
                .load(url)
                .error(R.drawable.ic_terrain_black_24dp)
                .into(coinViewHolder.logoImageView);
    }

    @Override
    public int getItemCount() {
        return coinLiveData.getValue().size();
    }


    class CoinViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.coin_logo) ImageView logoImageView;
        @BindView(R.id.coin_label) TextView labelTextView;

        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
