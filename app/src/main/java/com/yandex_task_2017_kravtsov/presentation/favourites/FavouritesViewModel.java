package com.yandex_task_2017_kravtsov.presentation.favourites;

import com.yandex_task_2017_kravtsov.domain.DataSource;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public final class FavouritesViewModel {

    private static final String TAG = FavouritesViewModel.class.getSimpleName();

    //// DEPENDENCY
    private final DataSource dataSource;

    private final BehaviorSubject<List<Translation>> favourites = BehaviorSubject.create();

    ////

    public FavouritesViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    ////

    BehaviorSubject<List<Translation>> getFavourites() {
        return favourites;
    }

    void searchFavourites(String searchCriteria) {
        dataSource
                .getFavouriteTranslations()
                .filter(translation -> translation.getFrom().contains(searchCriteria) ||
                        isStringArrayContains(translation.getTo(), searchCriteria))
                .toSortedList((o1, o2) -> (int) (o2.getDate() - o1.getDate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(favourites::onNext)
                .subscribe();
    }

    private boolean isStringArrayContains(String[] array, String text) {
        for (String str : array) {
            if (str.contains(text)) return true;
        }

        return false;
    }
}
