package com.yandex_task_2017_kravtsov.external.network.yandex;

import android.util.Log;

import com.yandex_task_2017_kravtsov.domain.Language;
import com.yandex_task_2017_kravtsov.external.network.HttpClient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class YandexApiAdapter implements HttpClient {

    private static final String TAG = YandexApiAdapter.class.getSimpleName();
    private static final int TRANSLATION_REQUEST_SUCCESS_CODE = 200;

    private YandexApiClient yandexApiClient;

    ////

    public YandexApiAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(YandexApiClient.BASE_URL)
                .build();

        yandexApiClient = retrofit.create(YandexApiClient.class);
    }

    //// HTTP CLIENT

    @Override
    public Observable<String[]> getTranslatedTo(String translateFrom, String translationDirection) {
        return yandexApiClient
                .getTranslatedTo(YandexApiClient.API_KEY, translateFrom, translationDirection)
                .filter(translateResponse -> translateResponse.getCode() == TRANSLATION_REQUEST_SUCCESS_CODE)
                .map(TranslateResponse::getText);
    }

    @Override
    public Observable<List<Language>> getSupportedLanguages(String uiLanguageCode) {
        return yandexApiClient
                .getSupportedLanguages(YandexApiClient.API_KEY, uiLanguageCode)
                .map(SupportedLanguagesResponse::getLanguages)
                .cache();
    }
}
