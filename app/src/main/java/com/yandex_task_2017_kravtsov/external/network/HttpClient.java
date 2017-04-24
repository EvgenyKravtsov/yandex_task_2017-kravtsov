package com.yandex_task_2017_kravtsov.external.network;

import com.yandex_task_2017_kravtsov.domain.Language;

import java.util.List;

import io.reactivex.Observable;

public interface HttpClient {

    Observable<String[]> getTranslatedTo(String translateFrom, String translationDirection);

    Observable<List<Language>> getSupportedLanguages(String uiLanguageCode);
}
