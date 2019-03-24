package com.arondillqs5328.magic.ui.fragment.coin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.adapter.CoinRecyclerAdapter;
import com.arondillqs5328.magic.androidX.MvpAppCompatFragment;
import com.arondillqs5328.magic.database.MagicDBHelper;
import com.arondillqs5328.magic.pojo.Coin;
import com.arondillqs5328.magic.presentation.presenter.coin.CoinPresenter;
import com.arondillqs5328.magic.presentation.view.coin.CoinView;

import java.util.List;


public class CoinFragment extends MvpAppCompatFragment implements CoinView {



    private ProgressBar progressBar;
    private  RecyclerView recyclerView;

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

        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.coin_recycler_view);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
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
