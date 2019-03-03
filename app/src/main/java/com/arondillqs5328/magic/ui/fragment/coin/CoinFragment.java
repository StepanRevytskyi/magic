package com.arondillqs5328.magic.ui.fragment.coin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arondillqs5328.magic.R;
import com.arondillqs5328.magic.adapter.CoinRecyclerAdapter;
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
    private LiveData<List<Coin>> coins;

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

        coins = presenter.getCoinLiveData();

        setUpRecyclerView();
        setUpCoinsLiveData();

        if (savedInstanceState == null) {
            Log.i("TAG_L", "last item = " + String.valueOf( ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()));
            Log.i("TAG_L", "item count = " + String.valueOf(recyclerView.getLayoutManager().getItemCount()));

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
        recyclerView.setAdapter(new CoinRecyclerAdapter(coins));
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

    private void setUpCoinsLiveData() {
        coins.observe(this, new Observer<List<Coin>>() {
            @Override
            public void onChanged(@Nullable List<Coin> coins) {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showProgressBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Log.i("TAG_L", "show progress bar");
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        Log.i("TAG_L", "hide progress bar");
    }

    @Override
    public void showFooter() {
        Log.i("TAG_L", "show footer");
    }

    @Override
    public void hideFooter() {
        Log.i("TAG_L", "hide footer");
    }
}
