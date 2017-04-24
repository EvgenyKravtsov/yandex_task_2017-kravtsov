package com.yandex_task_2017_kravtsov.presentation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yandex_task_2017_kravtsov.presentation.favourites.FavouritesFragment;
import com.yandex_task_2017_kravtsov.presentation.history.HistoryFragment;
import com.yandex_task_2017_kravtsov.presentation.translator.TranslatorFragment;

final class MainPagerAdapter extends FragmentPagerAdapter {

    MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //// FRAGMENT PAGER ADAPTER

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MainActivity.HISTORY_FRAGMENT_PAGER_NUMBER: return new HistoryFragment();
            case MainActivity.TRANSLATOR_FRAGMENT_PAGER_NUMBER: return new TranslatorFragment();
            case MainActivity.FAVOURITES_FRAGMENT_PAGER_NUMBER: return new FavouritesFragment();
            default: return new TranslatorFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
