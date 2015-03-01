package org.khacks.singandlearn.datastore;

import android.content.Context;

import com.google.gson.Gson;

import org.khacks.singandlearn.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by iain on 2/28/15.
 */
public class SongsOpenHelper extends SingToLearnOpenHelper {

    public static final String SONG_ID = "_id";
    public static final String SONG_ARTIST = "artist";
    public static final String SONG_NAME = "name";
    public static final String SONG_LYRICS = "lyrics";
    public static final String SONG_SCORE = "score";
    public static final String SONG_FILENAME = "filename";

    private static final int DATABASE_VERSION = 1;

    public static final String SONGS_TABLE_NAME = "songs";

    protected static final String SONGS_TABLE_CREATE =
            "CREATE TABLE " + SONGS_TABLE_NAME + " (" +
                    SONG_ID + " TEXT, " +
                    SONG_ARTIST + " TEXT, " +
                    SONG_NAME + " TEXT, " +
                    SONG_LYRICS + " TEXT, " +
                    SONG_FILENAME + " TEXT, " +
                    SONG_SCORE + " DOUBLE); ";

    SongsOpenHelper(Context context) {
        super(context, SONGS_TABLE_NAME, SONGS_TABLE_CREATE);
    }

    @Override
    public void onSetup() {
        Integer[][] songs = new Integer[][]{
                {R.raw.barbie_girl_data, R.raw.barbie_girl},
                {R.raw.i_gotta_feeling_data, R.raw.i_gotta_feeling},
                {R.raw.sweet_child_data, R.raw.sweet_child},
                {R.raw.cool_kids_data, R.raw.cool_kids},
                //{R.raw.vertigo_data, R.raw.vertigo}
        };
        for (Integer[] song : songs) {
            InputStream dataIn = context.getResources().openRawResource(song[0]);
            final Gson gson = new Gson();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(dataIn));
            addSong(gson.fromJson(reader, RawSongData.class));
        }
    }

    private void addSong(RawSongData data) {

    }
}
