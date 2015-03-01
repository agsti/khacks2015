package org.khacks.singandlearn.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by iain on 2/28/15.
 */
public class SongsDatastore {

    private final SingToLearnOpenHelper helper;



    public SongsDatastore(Context context) {
        helper = SingToLearnOpenHelper.getInstance(context);
        //helper.onSetup();
    }

    public long insertSong(String songId, String title, String artist, String filename, String jsonLyrics) {
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (title != null) {
            cv.put(SongsOpenHelper.SONG_NAME, title);
        }
        if (artist != null) {
            cv.put(SongsOpenHelper.SONG_ARTIST, artist);
        }
        cv.put(SongsOpenHelper.SONG_FILENAME, filename);
        cv.put(SongsOpenHelper.SONG_LYRICS, jsonLyrics);
        cv.put(SongsOpenHelper.SONG_ID, songId);
        return writableDatabase.insert(SongsOpenHelper.SONGS_TABLE_NAME, null, cv);
    }
    public void insertWord(Word w) {

    }

    public Song getSong(String songId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                SongsOpenHelper.SONGS_DATABASE_NAME,
                null,
                "id = ?",
                new String[]{songId},
                null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() == 0) return null;
        return new Song(cursor);
    }

    public Song getSongWithScores(String songId, WordsDatastore wordsDatastore) {
        Song song = getSong(songId);
        song.fetchScores(wordsDatastore);
        return song;
    }

    public Cursor getAllSongs() {

        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query( SongsOpenHelper.SONGS_TABLE_NAME,
                null, null, null,
                null, null, null);


    }



    public ArrayList<Song> getAllSongsWithScores(WordsDatastore wordsDatastore) {
        Cursor songCursor = getAllSongs();
        ArrayList<Song> songList = new ArrayList<>();
        while(songCursor.moveToNext()){
            Song s = new Song(songCursor);
            s.fetchScores(wordsDatastore);
            songList.add(s);
        }
        return songList;
    }

}
