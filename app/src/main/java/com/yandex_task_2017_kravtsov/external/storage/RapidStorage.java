package com.yandex_task_2017_kravtsov.external.storage;

import com.yandex_task_2017_kravtsov.domain.Language;

public interface RapidStorage {

    String loadPreferredLanguageCodeFrom();

    void savePreferredLanguageCodeFrom(String code);

    String loadPreferredLanguageCodeTo();

    void savePreferredLanguageCodeTo(String code);
}
