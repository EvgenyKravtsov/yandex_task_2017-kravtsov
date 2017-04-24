package com.yandex_task_2017_kravtsov.domain;

public final class Translation {

    private final long date; // Unix Milliseconds
    private final String from;
    private final String[] to;
    private final Language languageFrom;
    private final Language languageTo;

    ////

    public Translation(long date, String from, String[] to, Language languageFrom, Language languageTo) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
    }

    ////

    public long getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }

    public Language getLanguageFrom() {
        return languageFrom;
    }

    public Language getLanguageTo() {
        return languageTo;
    }
}
