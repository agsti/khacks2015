package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.khacks.singandlearn.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
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

    private static final int SONGS_DATABASE_VERSION = 1;
    static final String SONGS_DATABASE_NAME = "songs.db";

    public static final String SONGS_TABLE_NAME = "songs";

    protected static final String SONGS_TABLE_CREATE =
            "CREATE TABLE songs (" +
                    SONG_ID + " TEXT, " +
                    SONG_ARTIST + " TEXT, " +
                    SONG_NAME + " TEXT, " +
                    SONG_LYRICS + " TEXT, " +
                    SONG_FILENAME + " TEXT, " +
                    SONG_SCORE + " DOUBLE); ";

    SongsOpenHelper(Context context) {
        super(context, SONGS_TABLE_NAME, SONGS_TABLE_CREATE, SONGS_DATABASE_NAME, SONGS_DATABASE_VERSION);
    }

    @Override
    public void onSetup() {
        Integer[][] songs = new Integer[][]{
               {R.raw.barbie_girl_data,     R.raw.barbie_girl},
                {R.raw.i_gotta_feeling_data, R.raw.i_gotta_feeling},
                {R.raw.sweet_child_data,     R.raw.sweet_child},
                {R.raw.cool_kids_data,       R.raw.cool_kids},
                {R.raw.vertigo_data,         R.raw.vertigo_data}
        };
        for (Integer[] song : songs) {
            InputStream dataIn = this.context.getResources().openRawResource(song[0]);
            String songFilename = context.getApplicationContext().getResources().getResourceEntryName(song[1]);
            final Gson gson = new Gson();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(dataIn));
            RawSongData target = gson.fromJson(reader, RawSongData.class);
            addSong(target, songFilename, gson.toJson(target.lyrics));
        }
    }

    private void addSong(RawSongData data, String songFilename, String jsonData) {
        SongsDatastore songsDatastore = new SongsDatastore(this.context);
        for (RawWordData wordData : data.words.values()) {
            wordData.song_id = data.id;
            addWord(wordData);
        }
        songsDatastore.insertSong(data.id, data.title, data.artist, songFilename, jsonData);
    }

    private void addWord(RawWordData wordData) {
        WordsDatastore wordsDatastore = new WordsDatastore(this.context);
        wordsDatastore.insertWord(wordData.word, wordData.song_id, wordData.complexity, wordData.score, wordData.seen);
    }

}
