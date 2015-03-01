package org.khacks.singandlearn.datastore;

import android.database.Cursor;
import android.provider.UserDictionary;

/**
 * Created by iain on 2/28/15.
 */
public class Word {

    String id;
    private String word;
    private String song;
    boolean is_star;
    int correct;
    int attempts;

    double score;
    int seen;
    int complexity;

    String translation;
    private String[] similar_list;
    private boolean attempt;

    public Word(Cursor cursor) {
        this.word = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_WORD));
        this.song = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SONG)
        );
        this.is_star = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_ISSTAR)
        ) != 0;
        this.correct = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_CORRECT)
        );
        this.attempts = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_ATTEMPTS)
        );
        this.translation = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_TRANSLATION)
        );

        this.complexity = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_COMPLEXITY)
        );
        this.seen = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SEEN)
        );
        this.score = cursor.getDouble(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SCORE)
        );
    }

    public Word(String word, String song, String translation, String[] similar_list) {

    }

    private String[] getSimilarWords() {
        return similar_list;
    }

    private String getWord() {
        return word;
    }

    private  String getSong() {
        return song;
    }


    public void setAttempt(boolean attempt) {
        this.attempts += 1;
        if (attempt) {
            this.correct += 1;
        }
    }
}
