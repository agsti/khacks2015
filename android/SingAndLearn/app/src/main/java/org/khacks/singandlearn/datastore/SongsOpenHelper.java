package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iain on 2/28/15.
 */
public class SongsOpenHelper extends SingToLearnOpenHelper {

    private static final String SONG_ID = "id";
    private static final String SONG_ARTIST = "artist";
    private static final String SONG_NAME = "name";
    private static final String SONG_LYRICS = "lyrics";
    private static final String SONG_SCORE = "score";

    private static final int DATABASE_VERSION = 1;

    private static final String SONGS_TABLE_NAME = "songs";

    protected static final String SONGS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    SONG_ID + " TEXT, " +
                    SONG_ARTIST + " TEXT, " +
                    SONG_NAME + " TEXT, " +
                    SONG_LYRICS + " TEXT, " +
                    SONG_SCORE + " DOUBLE); ";

    SongsOpenHelper(Context context) {
        super(context, SONGS_TABLE_NAME, SONGS_TABLE_CREATE);
    }
}
