package org.khacks.singandlearn.datastore;

import android.database.Cursor;

/**
 * Created by iain on 2/28/15.
 */
public class Song {



    private final String name;
    private final String raw_lyrics;
    private final double score;
    private final String id;
    private final String artist;

    private double raw_tries;
    private double raw_score;

    public Song(Cursor cursor) {
        id = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ID));

        artist = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ARTIST));

        name = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_NAME));

        raw_lyrics = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_LYRICS));

        score = cursor.getDouble(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_SCORE));
    }

    public void fetchScores(WordsDatastore wordsDatastore) {
        Word.WordsScore score = wordsDatastore.getSongScore(this.id);


    }
}
