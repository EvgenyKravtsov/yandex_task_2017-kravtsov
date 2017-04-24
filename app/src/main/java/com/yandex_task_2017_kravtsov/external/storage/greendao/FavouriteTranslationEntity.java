package com.yandex_task_2017_kravtsov.external.storage.greendao;

import com.yandex_task_2017_kravtsov.domain.Language;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
final class FavouriteTranslationEntity {

    @Id
    private long id;

    @NotNull
    private String from;

    @NotNull
    private String to;

    @NotNull
    private String languageFrom;

    @NotNull
    private String languageTo;

    @Generated(hash = 677136460)
    public FavouriteTranslationEntity(long id, @NotNull String from,
            @NotNull String to, @NotNull String languageFrom,
            @NotNull String languageTo) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
    }

    @Generated(hash = 1241428742)
    public FavouriteTranslationEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLanguageFrom() {
        return this.languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTo() {
        return this.languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }
}
