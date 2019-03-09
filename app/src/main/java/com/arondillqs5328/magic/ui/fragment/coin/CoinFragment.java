package com.arondillqs5328.magic.ui.fragment.coin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.adapter.CoinRecyclerAdapter;
import com.arondillqs5328.magic.database.MagicDBHelper;
import com.arondillqs5328.magic.pojo.Coin;
import com.arondillqs5328.magic.presentation.presenter.coin.CoinPresenter;
import com.arondillqs5328.magic.presentation.view.coin.CoinView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinFragment extends MvpAppCompatFragment implements CoinView {

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.coin_recycler_view) RecyclerView recyclerView;

    @InjectPresenter CoinPresenter presenter;
    private LiveData<List<Coin>> coinLiveData;
    private LiveData<List<Integer>> favoriteCoinLiveData;
    private MagicDBHelper magicDBHelper = new MagicDBHelper();

    public static CoinFragment getInstance() {
        CoinFragment fragment = new CoinFragment();
        return fragment;
    }

    public CoinFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin, container, false);
        ButterKnife.bind(this, view);

        coinLiveData = presenter.getCoinLiveData();
        favoriteCoinLiveData = magicDBHelper.getCoin();

        setUpRecyclerView();
        setUpLiveData();

        if (savedInstanceState == null) {
            presenter.onLoadMore(
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition(),
                    recyclerView.getLayoutManager().getItemCount()
            );
        }
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CoinRecyclerAdapter(coinLiveData.getValue(), new MagicDBHelper()));
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

    private void setUpLiveData() {
        coinLiveData.observe(this, new Observer<List<Coin>>() {
            @Override
            public void onChanged(@Nullable List<Coin> coins) {
                ((CoinRecyclerAdapter) recyclerView.getAdapter()).setCoins(coins);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        favoriteCoinLiveData.observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                ((CoinRecyclerAdapter) recyclerView.getAdapter()).setFavoriteCoins(integers);
            }
        });
    }

    @Override
    public void showProgressBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFooter() {
    }

    @Override
    public void hideFooter() {
    }
}
