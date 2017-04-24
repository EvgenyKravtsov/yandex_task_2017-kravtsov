package com.yandex_task_2017_kravtsov.domain;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<String[]> getTranslatedTo(String translateFrom,
                                         Language languageFrom,
                                         Language languageTo);

    Observable<List<Language>> getSupportedLanguages();

    String getPreferredLanguageCodeFrom();

    void setPreferredLanguageCodeFrom(String code);

    String getPreferredLanguageCodeTo();

    void setPreferredLanguageCodeTo(String code);

    void addTranslationToFavourites(Translation translation);

    Observable<Translation> getHistoryTranslations();

    Observable<Translation> getFavouriteTranslations();
}
