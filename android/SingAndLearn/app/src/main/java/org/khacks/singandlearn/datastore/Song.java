package org.khacks.singandlearn.datastore;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iain on 2/28/15.
 */
public class Song {


    public final String name;
    public final String rawLyrics;
    private WordsScore score;
    public final String _id;
    public final String artist;
    public final String fileName;
    public final List<RawLyricsData> lyrics;

    public Song(Cursor cursor) {
        _id = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ID)
        );

        artist = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ARTIST)
        );

        name = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_NAME)
        );

        rawLyrics = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_LYRICS)
        );

        final Gson gson = new Gson();
        lyrics = gson.fromJson(rawLyrics, new ArrayList<RawLyricsData>().getClass());

        fileName = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_FILENAME)
        );

        // score = cursor.getDouble(
        //        cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_SCORE));
    }

    public void fetchScores(WordsDatastore wordsDatastore) {
        score = wordsDatastore.getSongWordsScore(this._id);
    }

    public boolean hasScore() {
        return score != null;
    }

    public double getRawTries() {
        return score.number_attempts;
    }
    public double getRawScore() {
        return score.number_successes / score.number_attempts;
    }

}
