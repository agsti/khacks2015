package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by iain on 2/28/15.
 */
public class WordsDatastore extends SingToLearnDatastore {
    private final WordsOpenHelper helper;

    WordsDatastore(Context context) {
        helper = new WordsOpenHelper(context);
    }
    public ArrayList<Word> getWordsStatus(String songId) {
        // fetch all words matching song with a nonzero score
        // serialize and return as Word objects w/ a score and attempts
        return new ArrayList<Word>();
    }
    public int getSongScore(String songId) {
        // fetch the overall score for all words in the song (averging)
        // return the avg score
        return 0; // range 0 - 100 as an integer
    }
    public Word.WordsScore getSongWordsScore(String songId) {
        SQLiteDatabase r = helper.getReadableDatabase();
        Cursor c = r.rawQuery("SELECT SUM("+helper.WORD_CORRECT +"), SUM("+helper.WORD_ATTEMPTS+") FROM " + helper.TABLE_NAME + " WHERE "+helper.WORD_SONG+" = ?",
                new String[]{songId});
        if (c.getColumnCount() > 0) {
            c.moveToFirst();
            Word.WordsScore wordsScore = new Word.WordsScore()();
            wordsScore.number_successes = c.getInt(0);
            wordsScore.number_attempts = c.getInt(1);
            return wordsScore;

        }
        return null;
    }
    public Word getWordChallenge(String wordId) {
        // fetch the word with the word id
        // load the related definitions from datastore
        // load the translation
        return null;
    }

}
