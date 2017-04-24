package com.yandex_task_2017_kravtsov.external.network.yandex;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface YandexApiClient {

    String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    String API_KEY = "trnsl.1.1.20170422T063659Z.e47d07f7db43a5a3.e0e930fcd9553deef38ed6c348da3f6e43282f9b";

    ////

    @GET("translate")
    Observable<TranslateResponse> getTranslatedTo(
            @Query("key") String apiKey,
            @Query("text") String translateFrom,
            @Query("lang") String translationDirection);
    @GET("getLangs")
    Observable<SupportedLanguagesResponse> getSupportedLanguages(
            @Query("key") String key,
            @Query("ui") String uiLanguageCode);
}
