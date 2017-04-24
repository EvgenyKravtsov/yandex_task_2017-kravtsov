package com.yandex_task_2017_kravtsov.presentation;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yandex_task_2017_kravtsov.R;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MainActivity extends AppCompatActivity {

    static final int HISTORY_FRAGMENT_PAGER_NUMBER = 0;
    static final int TRANSLATOR_FRAGMENT_PAGER_NUMBER = 1;
    static final int FAVOURITES_FRAGMENT_PAGER_NUMBER = 2;

    //// WIDGETS

    @BindColor(android.R.color.black)
    int blackColor;
    @BindColor(android.R.color.darker_gray)
    int grayColor;

    @BindDrawable(R.drawable.transparent_background)
    Drawable transparentBackgroundDrawable;
    @BindDrawable(R.drawable.black_border_bottom_white_background)
    Drawable blackBorderBottomWhiteBackgroundDrawable;

    @BindView(R.id.mainActivity_historyTabTextView)
    TextView historyTabTextView;
    @BindView(R.id.mainActivity_translatorTabTextView)
    TextView translatorTabTextView;
    @BindView(R.id.mainActivity_favouritesTabTextView)
    TextView favouritesTabTextView;
    @BindView(R.id.mainActivity_mainViewPager)
    ViewPager mainViewPager;

    //// ACTIVITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager();
        setupTranslatorTabActivated();
    }

    //// CONTROL CALLBACKS

    @OnClick(R.id.mainActivity_historyTabTextView)
    public void onClickHistoryTabTextView() {
        mainViewPager.setCurrentItem(HISTORY_FRAGMENT_PAGER_NUMBER);
    }

    @OnClick(R.id.mainActivity_translatorTabTextView)
    public void onClickTranslatorTabTextView() {
        mainViewPager.setCurrentItem(TRANSLATOR_FRAGMENT_PAGER_NUMBER);
    }

    @OnClick(R.id.mainActivity_favouritesTabTextView)
    public void onClickFavouritesTabTextView() {
        mainViewPager.setCurrentItem(FAVOURITES_FRAGMENT_PAGER_NUMBER);
    }

    //// PRIVATE

    private void setupViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(adapter);
        mainViewPager.setCurrentItem(TRANSLATOR_FRAGMENT_PAGER_NUMBER);
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case HISTORY_FRAGMENT_PAGER_NUMBER: setupHistoryTabActivated(); break;
                    case TRANSLATOR_FRAGMENT_PAGER_NUMBER: setupTranslatorTabActivated(); break;
                    case FAVOURITES_FRAGMENT_PAGER_NUMBER: setupFavouritesTabActivated(); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupHistoryTabActivated() {
        historyTabTextView.setTextColor(blackColor);
        historyTabTextView.setBackground(blackBorderBottomWhiteBackgroundDrawable);
        translatorTabTextView.setTextColor(grayColor);
        translatorTabTextView.setBackground(transparentBackgroundDrawable);
        favouritesTabTextView.setTextColor(grayColor);
        favouritesTabTextView.setBackground(transparentBackgroundDrawable);
    }

    private void setupTranslatorTabActivated() {
        historyTabTextView.setTextColor(grayColor);
        historyTabTextView.setBackground(transparentBackgroundDrawable);
        translatorTabTextView.setTextColor(blackColor);
        translatorTabTextView.setBackground(blackBorderBottomWhiteBackgroundDrawable);
        favouritesTabTextView.setTextColor(grayColor);
        favouritesTabTextView.setBackground(transparentBackgroundDrawable);
    }

    private void setupFavouritesTabActivated() {
        historyTabTextView.setTextColor(grayColor);
        historyTabTextView.setBackground(transparentBackgroundDrawable);
        translatorTabTextView.setTextColor(grayColor);
        translatorTabTextView.setBackground(transparentBackgroundDrawable);
        favouritesTabTextView.setTextColor(blackColor);
        favouritesTabTextView.setBackground(blackBorderBottomWhiteBackgroundDrawable);
    }
}




































