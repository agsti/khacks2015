package org.khacks.singandlearn.datastore;

import android.content.Context;

/**
 * Created by iain on 2/28/15.
 */
public class WordsOpenHelper extends SingToLearnOpenHelper {

    private static final String WORD_ID = "id";
    static final String WORD_WORD = "word";
    static final String WORD_SONG = "song";
    static final String WORD_CORRECT = "correct";
    static final String WORD_ISSTAR = "is_star";
    static final String WORD_ATTEMPTS = "attempts";
    static final String WORD_COMPLEXITY = "complexity";
    static final String WORD_SEEN = "seen";
    static final String WORD_SCORE = "score";
    static final String WORD_TRANSLATION = "translation";
    private static final String WORD_SIMILAR = "similar_list";

    static final String WORDS_TABLE_NAME = "words";


    protected static final String WORDS_TABLE_CREATE =
            "CREATE TABLE " + WORDS_TABLE_NAME + " (" +
                    WORD_ID +         " TEXT NOT NULL PRIMARY KEY, " +
                    WORD_WORD +       " TEXT NOT NULL, " +
                    WORD_SONG  +      " TEXT NOT NULL, " +
                    WORD_ISSTAR +     " INTEGER NOT NULL DEFAULT 0, " +
                    WORD_COMPLEXITY + " INTEGER NOT NULL, " +
                    WORD_SCORE +      " INTEGER NOT NULL, " +
                    WORD_SEEN +       " INTEGER NOT NULL, " +
                    WORD_CORRECT +    " INT NOT NULL DEFAULT 0, " +
                    WORD_ATTEMPTS +   " INT NOT NULL DEFAULT 0, " +
                    WORD_TRANSLATION + " TEXT); ";

    WordsOpenHelper(Context context) {
        super(context, WORDS_TABLE_NAME, WORDS_TABLE_CREATE);
    }

}
