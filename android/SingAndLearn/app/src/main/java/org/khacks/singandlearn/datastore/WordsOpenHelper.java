package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iain on 2/28/15.
 */
public class WordsOpenHelper extends SingToLearnOpenHelper {

    private static final String WORD_ID = "id";
    private static final String WORD_WORD = "word";
    static final String WORD_SONG = "song";
    static final String WORD_CORRECT = "correct";
    static final String WORD_ATTEMPTS = "attempts";
    private static final String WORD_TRANSLATION = "translation";
    private static final String WORD_SIMILAR = "";

    private static final String WORDS_TABLE_NAME = "words";

    protected static final String WORDS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    WORD_ID + " TEXT, " +
                    WORD_WORD+ " TEXT, " +
                    WORD_SONG  + " TEXT, " +
                    WORD_CORRECT + " DOUBLE, " +
                    WORD_ATTEMPTS + " INT, " +
                    WORD_TRANSLATION + " TEXT); ";

    WordsOpenHelper(Context context) {
        super(context, WORDS_TABLE_NAME, WORDS_TABLE_CREATE);
    }


}
