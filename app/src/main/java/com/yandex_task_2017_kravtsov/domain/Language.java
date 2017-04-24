package com.yandex_task_2017_kravtsov.domain;

public final class Language {

    public static final String UI_LANGUAGE_CODE = "ru";

    private final String title;
    private final String code;

    ////

    public Language(String title, String code) {
        this.title = title;
        this.code = code;
    }

    ////

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    //// OBJECT

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Language && getCode().equals(((Language) obj).getCode());
    }
}
