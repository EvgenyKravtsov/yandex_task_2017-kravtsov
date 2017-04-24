package com.yandex_task_2017_kravtsov.external.storage.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yandex_task_2017_kravtsov.domain.Language;
import com.yandex_task_2017_kravtsov.domain.Translation;
import com.yandex_task_2017_kravtsov.external.storage.RdbStorage;

import java.util.List;

import io.reactivex.Observable;

public final class GreenDaoSqlite implements RdbStorage {

    private static final String TAG = GreenDaoSqlite.class.getSimpleName();
    private static final String STRING_ARRAY_SEPARATOR = ",";

    private final DaoSession daoSession;

    ////

    public GreenDaoSqlite(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(
                context,
                "translator_app_sqlite_database",
                null);

        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /// RDB STORAGE

    @Override
    public void saveTranslation(Translation translation) {
        TranslationEntityDao entityDao = daoSession.getTranslationEntityDao();

        List<TranslationEntity> translationEntities = entityDao.loadAll();
        for (TranslationEntity translationEntity : translationEntities) {
            if (translationEntity.getFrom().equals(translation.getFrom()) ||
                    translationEntity.getTo().equals(mapStringArrayToSeparatedString(
                            translation.getTo(),
                            STRING_ARRAY_SEPARATOR))) return;
        }

        TranslationEntity translationEntity = new TranslationEntity(
                translation.getDate(),
                translation.getFrom(),
                mapStringArrayToSeparatedString(translation.getTo(), STRING_ARRAY_SEPARATOR),
                mapLanguageToString(translation.getLanguageFrom()),
                mapLanguageToString(translation.getLanguageTo()));
        entityDao.insert(translationEntity);
    }

    @Override
    public void saveFavouriteTranslation(Translation translation) {
        FavouriteTranslationEntityDao entityDao = daoSession.getFavouriteTranslationEntityDao();

        List<FavouriteTranslationEntity> translationEntities = entityDao.loadAll();
        for (FavouriteTranslationEntity translationEntity : translationEntities) {
            if (translationEntity.getFrom().equals(translation.getFrom()) ||
                    translationEntity.getTo().equals(mapStringArrayToSeparatedString(
                            translation.getTo(),
                            STRING_ARRAY_SEPARATOR))) return;
        }

        FavouriteTranslationEntity translationEntity = new FavouriteTranslationEntity(
                translation.getDate(),
                translation.getFrom(),
                mapStringArrayToSeparatedString(translation.getTo(), ","),
                mapLanguageToString(translation.getLanguageFrom()),
                mapLanguageToString(translation.getLanguageTo()));
        entityDao.insert(translationEntity);
    }

    @Override
    public Observable<Translation> getHistoryTranslations() {
        List<TranslationEntity> translationEntities = daoSession.getTranslationEntityDao().loadAll();
        return Observable.fromArray(translationEntities.toArray(new TranslationEntity[translationEntities.size()]))
                .map(this::mapTranslationEntityToTranslation);
    }

    @Override
    public Observable<Translation> getFavouriteTranslations() {
        List<FavouriteTranslationEntity> translationEntities = daoSession.getFavouriteTranslationEntityDao().loadAll();
        return Observable.fromArray(translationEntities.toArray(new FavouriteTranslationEntity[translationEntities.size()]))
                .map(this::mapFavouriteTranslationEntityToTranslation);
    }
    ////

    private String mapStringArrayToSeparatedString(String[] array, String separator) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) builder.append(separator);
        }

        return builder.toString();
    }

    private String mapLanguageToString(Language language) {
        return language.getTitle() + STRING_ARRAY_SEPARATOR + language.getCode();
    }

    private Language mapStringToLanguage(String string) {
        String[] fields = string.split(STRING_ARRAY_SEPARATOR);
        return new Language(fields[0], fields[1]);
    }

    private Translation mapTranslationEntityToTranslation(TranslationEntity translationEntity) {
        return new Translation(
                translationEntity.getId(),
                translationEntity.getFrom(),
                translationEntity.getTo().split(STRING_ARRAY_SEPARATOR),
                mapStringToLanguage(translationEntity.getLanguageFrom()),
                mapStringToLanguage(translationEntity.getLanguageTo())
        );
    }

    private Translation mapFavouriteTranslationEntityToTranslation(FavouriteTranslationEntity translationEntity) {
        return new Translation(
                translationEntity.getId(),
                translationEntity.getFrom(),
                translationEntity.getTo().split(STRING_ARRAY_SEPARATOR),
                mapStringToLanguage(translationEntity.getLanguageFrom()),
                mapStringToLanguage(translationEntity.getLanguageTo())
        );
    }
}
