package com.yandex_task_2017_kravtsov.domain;

import com.yandex_task_2017_kravtsov.external.network.HttpClient;
import com.yandex_task_2017_kravtsov.external.storage.RapidStorage;
import com.yandex_task_2017_kravtsov.external.storage.RdbStorage;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;

public final class DataSourceCombined implements DataSource {

    private static final String TAG = DataSourceCombined.class.getSimpleName();

    //// DEPENDENCY
    private final HttpClient httpClient;
    private final RapidStorage rapidStorage;
    private final RdbStorage rdbStorage;

    ////

    public DataSourceCombined(HttpClient httpClient, RapidStorage rapidStorage, RdbStorage rdbStorage) {
        this.httpClient = httpClient;
        this.rapidStorage = rapidStorage;
        this.rdbStorage = rdbStorage;
    }

    //// DATA SOURCE

    @Override
    public Observable<String[]> getTranslatedTo(String translateFrom,
                                                Language languageFrom,
                                                Language languageTo) {
        return httpClient
                .getTranslatedTo(translateFrom, setupTranslationDirection(languageFrom, languageTo))
                .doOnNext(translations -> rdbStorage.saveTranslation(new Translation(
                        Calendar.getInstance().getTimeInMillis(),
                        translateFrom,
                        translations,
                        languageFrom,
                        languageTo)
                ));
    }

    @Override
    public Observable<List<Language>> getSupportedLanguages() {
        return httpClient.getSupportedLanguages(Language.UI_LANGUAGE_CODE);
    }

    @Override
    public String getPreferredLanguageCodeFrom() {
        return rapidStorage.loadPreferredLanguageCodeFrom();
    }

    @Override
    public void setPreferredLanguageCodeFrom(String code) {
        rapidStorage.savePreferredLanguageCodeFrom(code);
    }

    @Override
    public String getPreferredLanguageCodeTo() {
        return rapidStorage.loadPreferredLanguageCodeTo();
    }

    @Override
    public void setPreferredLanguageCodeTo(String code) {
        rapidStorage.savePreferredLanguageCodeTo(code);
    }

    @Override
    public void addTranslationToFavourites(Translation translation) {
        rdbStorage.saveFavouriteTranslation(translation);
    }

    @Override
    public Observable<Translation> getHistoryTranslations() {
        return rdbStorage.getHistoryTranslations();
    }

    @Override
    public Observable<Translation> getFavouriteTranslations() {
        return rdbStorage.getFavouriteTranslations();
    }

    //// PRIVATE

    private String setupTranslationDirection(Language languageFrom, Language languageTo) {
        return String.format(
                "%s-%s",
                languageFrom.getCode(),
                languageTo.getCode()
        );
    }
}
