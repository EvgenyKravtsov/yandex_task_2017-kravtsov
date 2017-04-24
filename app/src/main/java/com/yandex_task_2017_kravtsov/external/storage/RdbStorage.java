package com.yandex_task_2017_kravtsov.external.storage;

import com.yandex_task_2017_kravtsov.domain.Translation;

import io.reactivex.Observable;

public interface RdbStorage {

    void saveTranslation(Translation translation);

    void saveFavouriteTranslation(Translation translation);

    Observable<Translation> getHistoryTranslations();

    Observable<Translation> getFavouriteTranslations();
}
