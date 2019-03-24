package com.arondillqs5328.magic.ui.fragment.coin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.adapter.CoinRecyclerAdapter;
import com.arondillqs5328.magic.androidX.MvpAppCompatFragment;
import com.arondillqs5328.magic.database.MagicDBHelper;
import com.arondillqs5328.magic.model.pojo.Coin;
import com.arondillqs5328.magic.presentation.presenter.coin.CoinPresenter;
import com.arondillqs5328.magic.presentation.view.coin.CoinView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CoinFragment extends MvpAppCompatFragment implements CoinView {

    @InjectPresenter CoinPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private LiveData<List<Coin>> coinLD;
    private LiveData<List<Integer>> favoriteLD;
    private MagicDBHelper magicDBHelper = new MagicDBHelper();

    public static CoinFragment getInstance() {
        return new CoinFragment();
    }

    public CoinFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        progressBar = view.findViewById(R.id.coin_progress_bar);
        recyclerView = view.findViewById(R.id.coin_recycler_view);

        coinLD = presenter.getCoinsLiveData();
        favoriteLD = magicDBHelper.getCoin();

        setupRecyclerView();
        setupLiveData();

        if (savedInstanceState == null) {
            presenter.onLoadMore(
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition(),
                    recyclerView.getLayoutManager().getItemCount()
            );
        }
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new CoinRecyclerAdapter(coinLD.getValue(), new MagicDBHelper()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                presenter.onLoadMore(
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition(),
                        recyclerView.getLayoutManager().getItemCount()
                );
            }
        });
    }

    private void setupLiveData() {
        coinLD.observe(this, new Observer<List<Coin>>() {
            @Override
            public void onChanged(@Nullable List<Coin> coins) {
                ((CoinRecyclerAdapter) recyclerView.getAdapter()).setupCoins(coins);
            }
        });

        favoriteLD.observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                ((CoinRecyclerAdapter) recyclerView.getAdapter()).setupFavoriteCoins(integers);
            }
        });
    }

    @Override
    public void showProgressBar() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
