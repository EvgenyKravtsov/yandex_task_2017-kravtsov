package com.yandex_task_2017_kravtsov;

import com.yandex_task_2017_kravtsov.domain.DataSource;
import com.yandex_task_2017_kravtsov.domain.DataSourceCombined;
import com.yandex_task_2017_kravtsov.external.network.HttpClient;
import com.yandex_task_2017_kravtsov.external.storage.greendao.GreenDaoSqlite;
import com.yandex_task_2017_kravtsov.external.network.yandex.YandexApiAdapter;
import com.yandex_task_2017_kravtsov.external.storage.RapidStorage;
import com.yandex_task_2017_kravtsov.external.storage.RdbStorage;
import com.yandex_task_2017_kravtsov.external.storage.android.SharedPreferencesStorage;
import com.yandex_task_2017_kravtsov.presentation.favourites.FavouritesViewModel;
import com.yandex_task_2017_kravtsov.presentation.history.HistoryViewModel;
import com.yandex_task_2017_kravtsov.presentation.translator.TranslatorViewModel;

public final class DependencyInjection {

    private static DataSource dataSource;
    private static HttpClient httpClient;
    private static RdbStorage rdbStorage;

    //// DOMAIN

    private static DataSource provideDataSource() {
        if (dataSource == null) {
            dataSource = new DataSourceCombined(
                    provideHttpClient(),
                    provideRapidStorage(),
                    provideRdbStorage());
        }

        return dataSource;
    }

    //// EXTERNAL

    private static HttpClient provideHttpClient() {
        if (httpClient == null) {
            httpClient = new YandexApiAdapter();
        }

        return httpClient;
    }

    private static RapidStorage provideRapidStorage() {
        return new SharedPreferencesStorage(AppController.getAppContext());
    }

    private static RdbStorage provideRdbStorage() {
        if (rdbStorage == null) {
            rdbStorage = new GreenDaoSqlite(AppController.getAppContext());
        }

        return rdbStorage;
    }

    //// VIEW MODELS

    public static TranslatorViewModel provideTranslatorViewModel() {
        return new TranslatorViewModel(provideDataSource());
    }

    public static HistoryViewModel provideHistoryViewModel() {
        return new HistoryViewModel(provideDataSource());
    }

    public static FavouritesViewModel provideFavouritesViewModel() {
        return new FavouritesViewModel(provideDataSource());
    }
}
