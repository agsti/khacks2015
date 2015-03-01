package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import org.khacks.singandlearn.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by iain on 2/28/15.
 */
public class SingToLearnOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "song_db";
    private static final int DATABASE_VERSION = 1;

    protected final Context context;

    private static SingToLearnOpenHelper instance;

    public static SingToLearnOpenHelper getInstance(Context c){
        if(instance == null){
            instance = new SingToLearnOpenHelper(c);
        }
        return instance;
    }


    private SingToLearnOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXISTS " + SongsOpenHelper.SONGS_TABLE_NAME+ ";");
        db.execSQL("DROP IF EXISTS " + WordsOpenHelper.WORDS_TABLE_NAME+ ";");
        db.execSQL(SongsOpenHelper.SONGS_TABLE_CREATE);
        db.execSQL(WordsOpenHelper.WORDS_TABLE_CREATE);
    }

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SongsOpenHelper.SONGS_TABLE_CREATE);
        db.execSQL(WordsOpenHelper.WORDS_TABLE_CREATE);

    }


}
