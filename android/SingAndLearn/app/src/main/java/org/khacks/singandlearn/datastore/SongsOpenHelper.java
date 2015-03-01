package org.khacks.singandlearn.datastore;

/**
 * Created by iain on 2/28/15.
 */
public class SongsOpenHelper{

    public static final String SONG_ID = "_id";
    public static final String SONG_ARTIST = "artist";
    public static final String SONG_NAME = "name";
    public static final String SONG_LYRICS = "lyrics";
    public static final String SONG_SCORE = "score";
    public static final String SONG_FILENAME = "filename";

    private static final int SONGS_DATABASE_VERSION = 2;
    static final String SONGS_DATABASE_NAME = "songs.db";

    public static final String SONGS_TABLE_NAME = "songs";

    protected static final String SONGS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS songs (" +
                    SONG_ID + " TEXT, " +
                    SONG_ARTIST + " TEXT, " +
                    SONG_NAME + " TEXT, " +
                    SONG_LYRICS + " TEXT, " +
                    SONG_FILENAME + " TEXT, " +
                    SONG_SCORE + " DOUBLE); ";





}
