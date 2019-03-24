package com.arondillqs5328.magic.adapter;

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
import com.arondillqs5328.magic.model.pojo.Coin;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoinRecyclerAdapter extends RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder> {

    private List<Coin> coins;
    private List<Integer> favoriteCoins;
    private MagicDBHelper magicDBHelper;
    private final SparseBooleanArray selectedCoins = new SparseBooleanArray();

    private final String url = "https://s2.coinmarketcap.com/static/img/coins/128x128/";

    public CoinRecyclerAdapter(List<Coin> coins, MagicDBHelper magicDBHelper) {
        this.coins = coins;
        this.magicDBHelper = magicDBHelper;
    }

    public void setupCoins(List<Coin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    public void setupFavoriteCoins(List<Integer> favoriteCoins) {
        this.favoriteCoins = favoriteCoins;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coin, viewGroup, false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CoinViewHolder holder, int i) {
        holder.nameTextView.setText(coins.get(i).getName());
        Picasso.get()
                .load(url + coins.get(i).getId() + ".png")
                .into(holder.iconImageView);

        Integer id = coins.get(i).getId();

        if (favoriteCoins.contains(id)) {
            holder.favoriteCheckBox.setChecked(true);
        } else {
            holder.favoriteCheckBox.setChecked(selectedCoins.get(i, false));
        }

        holder.favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!favoriteCoins.contains(coins.get(holder.getAdapterPosition()).getId())) {
                        magicDBHelper.insertCoinIntoDB(coins.get(holder.getAdapterPosition()));
                    }
                } else {
                    if (favoriteCoins.contains(coins.get(holder.getAdapterPosition()).getId())) {
                        magicDBHelper.deleteCoinFromDB(coins.get(holder.getAdapterPosition()).getId());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    class CoinViewHolder extends RecyclerView.ViewHolder {

        private ImageView iconImageView;
        private TextView nameTextView;
        private CheckBox favoriteCheckBox;

        CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.coin_icon);
            nameTextView = itemView.findViewById(R.id.coin_name);
            favoriteCheckBox = itemView.findViewById(R.id.coin_favorite_checkbox);
        }
    }
}
