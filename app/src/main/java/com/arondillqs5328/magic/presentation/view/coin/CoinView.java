package com.arondillqs5328.magic.presentation.view.coin;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.arondillqs5328.magic.strategy.AddToEndSingleByTagStateStrategy;

public interface CoinView extends MvpView {

    String TAG_PROGRESS_BAR = "tagProgressBar";
    String TAG_FOOTER = "tagFooter";

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_PROGRESS_BAR)
    void showProgressBar();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_PROGRESS_BAR)
    void hideProgressBar();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_FOOTER)
    void showFooter();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_FOOTER)
    void hideFooter();

}
