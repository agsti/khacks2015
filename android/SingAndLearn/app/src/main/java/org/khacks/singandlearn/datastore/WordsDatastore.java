package org.khacks.singandlearn.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.UUID;

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

    public void saveAttempt(Word word, boolean correct) {
        String wordId = word.id;
        word.setAttempt(correct);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WordsOpenHelper.WORD_CORRECT, word.correct);
        cv.put(WordsOpenHelper.WORD_ATTEMPTS, word.attempts);
        writableDatabase.update(WordsOpenHelper.WORDS_TABLE_NAME, cv, WordsOpenHelper.WORD_SONG + " = ?", new String[]{word.id});

    }
    public void insertWord(String word, String song, int complexity, double score, int seen) {
        String  uniqueID = UUID.randomUUID().toString();
        ContentValues cv = new ContentValues();
        cv.put(WordsOpenHelper.WORD_WORD, word);
        cv.put(WordsOpenHelper.WORD_SONG, song);
        cv.put(WordsOpenHelper.WORD_COMPLEXITY, complexity);
        cv.put(WordsOpenHelper.WORD_SCORE, score);
        cv.put(WordsOpenHelper.WORD_SEEN, seen);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(WordsOpenHelper.WORDS_TABLE_NAME, null, cv);
    }


    public WordsScore getSongWordsScore(String songId) {
        SQLiteDatabase r = helper.getReadableDatabase();
        Cursor c = r.rawQuery(
                "SELECT SUM(" + helper.WORD_CORRECT + "), " +
                "SUM(" + helper.WORD_ATTEMPTS + ") FROM " + helper.TABLE_NAME +
                " WHERE " + helper.WORD_SONG + " = ?",
                new String[]{songId});

        if (c.getColumnCount() > 0) {
            c.moveToFirst();
            WordsScore wordsScore = new WordsScore();
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
