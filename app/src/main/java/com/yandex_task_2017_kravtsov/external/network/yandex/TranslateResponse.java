package com.yandex_task_2017_kravtsov.external.network.yandex;

final class TranslateResponse {

    private final int code;
    private final String translationDirection;
    private final String[] text;

    ////

    TranslateResponse(int code, String translationDirection, String[] text) {
        this.code = code;
        this.translationDirection = translationDirection;
        this.text = text;
    }

    ////

    int getCode() {
        return code;
    }

    String getTranslationDirection() {
        return translationDirection;
    }

    String[] getText() {
        return text;
    }
}
