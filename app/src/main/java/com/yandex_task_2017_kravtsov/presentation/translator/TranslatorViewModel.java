package com.yandex_task_2017_kravtsov.presentation.translator;

import com.yandex_task_2017_kravtsov.domain.DataSource;
import com.yandex_task_2017_kravtsov.domain.Language;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public final class TranslatorViewModel {

    //// DEPENDENCY
    private final DataSource dataSource;

    private final BehaviorSubject<String> alert = BehaviorSubject.create();
    private final BehaviorSubject<List<Language>> languagesFrom = BehaviorSubject.create();
    private final BehaviorSubject<Language> selectedLanguageFrom = BehaviorSubject.create();
    private final BehaviorSubject<List<Language>> languagesTo = BehaviorSubject.create();
    private final BehaviorSubject<Language> selectedLanguageTo = BehaviorSubject.create();
    private final BehaviorSubject<String[]> translatedTo = BehaviorSubject.create();

    ////

    public TranslatorViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
        setupSupportedLanguages();
    }

    ////

    BehaviorSubject<String> getAlert() {
        return alert;
    }

    BehaviorSubject<List<Language>> getLanguagesFrom() {
        return languagesFrom;
    }

    BehaviorSubject<Language> getSelectedLanguageFrom() {
        return selectedLanguageFrom;
    }

    BehaviorSubject<List<Language>> getLanguagesTo() {
        return languagesTo;
    }

    BehaviorSubject<Language> getSelectedLanguageTo() {
        return selectedLanguageTo;
    }

    BehaviorSubject<String[]> getTranslatedTo() {
        return translatedTo;
    }

    void translateFrom(String translateFrom, Language languageFrom, Language languageTo) {
        if (translateFrom.equals("")) {
            this.translatedTo.onNext(new String[]{});
            return;
        }

        dataSource
                .getTranslatedTo(translateFrom, languageFrom, languageTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this.translatedTo::onNext)
                .subscribe();
    }

    void languageFromSelected(Language language) {
        dataSource.setPreferredLanguageCodeFrom(language.getCode());
    }

    void languageToSelected(Language language) {
        dataSource.setPreferredLanguageCodeTo(language.getCode());
    }

    void languagesSwapped() {
        String languageCodeFrom = dataSource.getPreferredLanguageCodeFrom();
        String swap = dataSource.getPreferredLanguageCodeTo();
        dataSource.setPreferredLanguageCodeTo(languageCodeFrom);
        dataSource.setPreferredLanguageCodeFrom(swap);
        setupSupportedLanguages();
    }

    void addedToFavourites(Translation translation) {
        if (!translation.getFrom().equals("") && translation.getTo().length > 0)
            dataSource.addTranslationToFavourites(translation);
        else
            alert.onNext("Nothing To Add");
    }

    //// PRIVATE

    private void setupSupportedLanguages() {
        dataSource
                .getSupportedLanguages()
                .flatMapIterable(languages -> languages)
                .filter(language -> language.getTitle() != null)
                .sorted((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getTitle(), o2.getTitle()))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this.languagesFrom::onNext)
                .doOnNext(this.languagesTo::onNext)
                .doOnNext(this::setupSelectedLanguages)
                .subscribe();
    }

    private void setupSelectedLanguages(List<Language> languages) {
        for (Language language : languages) {
            if (language.getCode().equals(dataSource.getPreferredLanguageCodeFrom())) {
                selectedLanguageFrom.onNext(language);
            }

            if (language.getCode().equals(dataSource.getPreferredLanguageCodeTo())) {
                selectedLanguageTo.onNext(language);
            }
        }
    }
}










































