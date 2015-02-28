package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by iain on 2/28/15.
 */
public class SongsDatastore {

    private final SongsOpenHelper helper;

    public SongsDatastore(Context context) {
        helper = new SongsOpenHelper(context);
    }

    public void updateWord(Word w) {

    }
    public void insertWord(Word w) {

    }

    public Song getSong(String songId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                SongsOpenHelper.TABLE_NAME,
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

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                SongsOpenHelper.TABLE_NAME,
                null, null, null,
                null, null, null);

        while (cursor.moveToNext()) {
            songs.add(new Song(cursor));
        }
        return songs;
    }

    private ArrayList<Song> fetchSong(String where) {
        if (where != null) {

        }
        return null;
    }

    public ArrayList<Song> getAllSongsWithScores(WordsDatastore wordsDatastore) {
        ArrayList<Song> allSongs = getAllSongs();
        for (Song s : allSongs) {
            s.fetchScores(wordsDatastore);
        }
        return allSongs;
    }

}
