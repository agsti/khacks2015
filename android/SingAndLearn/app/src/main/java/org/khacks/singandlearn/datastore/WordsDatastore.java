package org.khacks.singandlearn.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by iain on 2/28/15.
 */
public class WordsDatastore extends SingToLearnDatastore {
    private final SingToLearnOpenHelper helper;

    public WordsDatastore(Context context) {
        helper = SingToLearnOpenHelper.getInstance(context);
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
        writableDatabase.update(WordsOpenHelper.WORDS_TABLE_NAME, cv, WordsOpenHelper.WORD_ID + " = ?", new String[]{word.id});

    }
    public void insertWord(String word, String song, int complexity, double score, int seen, String wordAt) {
        String uniqueID = UUID.randomUUID().toString();
        ContentValues cv = new ContentValues();
        cv.put(WordsOpenHelper.WORD_WORD, word);
        cv.put(WordsOpenHelper.WORD_ID, uniqueID);
        cv.put(WordsOpenHelper.WORD_SONG, song);
        cv.put(WordsOpenHelper.WORD_AT, wordAt);
        cv.put(WordsOpenHelper.WORD_COMPLEXITY, complexity);
        cv.put(WordsOpenHelper.WORD_SCORE, score);
        cv.put(WordsOpenHelper.WORD_SEEN, seen);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(WordsOpenHelper.WORDS_TABLE_NAME, null, cv);
    }

    public WordsScore getSongWordsScore(String songId) {
        SQLiteDatabase r = helper.getReadableDatabase();
        Cursor c = r.rawQuery(
                "SELECT SUM(" + WordsOpenHelper.WORD_CORRECT + "), " +
                "SUM(" + WordsOpenHelper.WORD_ATTEMPTS + ") FROM " + WordsOpenHelper.WORDS_TABLE_NAME +
                " WHERE " + WordsOpenHelper.WORD_SONG + " = ?",
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


    public Word[] getRandomWords(int howmany, String songName){
        Word[] words = new Word[howmany];

        Cursor c = helper.getReadableDatabase().query(
                WordsOpenHelper.WORDS_TABLE_NAME,
                null,
                WordsOpenHelper.WORD_COMPLEXITY+"> 3 AND "+WordsOpenHelper.WORD_SONG+"= ?",
                new String[]{songName},
                null,
                null,
                WordsOpenHelper.WORD_COMPLEXITY, String.valueOf(howmany));
        int i = 0;
        while (c.moveToNext()){
            Word w = new Word(c);
            words[i] = w;
            i++;
        }
        return words;
    }

    public Word getWord(String songId, String wordStr) {
        SQLiteDatabase r = helper.getReadableDatabase();
        Cursor c = r.rawQuery(
                "SELECT * " +
                        " FROM " + WordsOpenHelper.WORDS_TABLE_NAME +
                        " WHERE " + WordsOpenHelper.WORD_SONG + " = ? AND " + WordsOpenHelper.WORD_WORD + " = ?",
                new String[]{songId, wordStr});

        if (c.getColumnCount() > 0) {
            c.moveToFirst();
            Word word2 = new Word(c);
            return word2;
        }
        return null;
    }
}
