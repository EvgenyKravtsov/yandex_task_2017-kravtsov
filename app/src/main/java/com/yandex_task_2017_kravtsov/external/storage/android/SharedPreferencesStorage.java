package com.yandex_task_2017_kravtsov.external.storage.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yandex_task_2017_kravtsov.external.storage.RapidStorage;

public final class SharedPreferencesStorage implements RapidStorage {

    private static final String TAG = SharedPreferencesStorage.class.getSimpleName();
    private static final String STORAGE_KEY = "storage_key";
    private static final String PREFERRED_LANGUAGE_CODE_FROM_KEY = "preferred_language_from_key";
    private static final String PREFERRED_LANGUAGE_CODE_FROM_DEFAULT = "en";
    private static final String PREFERRED_LANGUAGE_CODE_TO_KEY = "preferred_language_to_key";
    private static final String PREFERRED_LANGUAGE_CODE_TO_DEFAULT = "ru";

    private SharedPreferences sharedPreferences;

    ////

    public SharedPreferencesStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE);
    }

    //// RAPID STORAGE

    @Override
    public String loadPreferredLanguageCodeFrom() {
        return sharedPreferences.getString(
                PREFERRED_LANGUAGE_CODE_FROM_KEY,
                PREFERRED_LANGUAGE_CODE_FROM_DEFAULT);
    }

    @Override
    public void savePreferredLanguageCodeFrom(String code) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERRED_LANGUAGE_CODE_FROM_KEY, code);
        editor.apply();
    }

    @Override
    public String loadPreferredLanguageCodeTo() {
        return sharedPreferences.getString(
                PREFERRED_LANGUAGE_CODE_TO_KEY,
                PREFERRED_LANGUAGE_CODE_TO_DEFAULT);
    }

    @Override
    public void savePreferredLanguageCodeTo(String code) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERRED_LANGUAGE_CODE_TO_KEY, code);
        editor.apply();
    }
}
