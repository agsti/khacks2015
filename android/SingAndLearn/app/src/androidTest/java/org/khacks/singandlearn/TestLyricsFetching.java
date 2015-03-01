package org.khacks.singandlearn;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import org.khacks.singandlearn.datastore.SingToLearnOpenHelper;
import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;

/**
 * Created by iain on 3/1/15.
 */
public class TestLyricsFetching extends AndroidTestCase {
    private SongsDatastore songsDatastore;
    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        songsDatastore = new SongsDatastore(context);
    }
    public void testGetEntries() {
        SingToLearnOpenHelper.getInstance(getContext()).onSetup();

        assertEquals(songsDatastore.getAllSongs().getCount(), 5);
        Song song = songsDatastore.getSong("LY6be37788-bfb7-11e4-a9a8-20c9d0465fa9");
        Song.LyricsResult r = song.getLyricsAtPosition((float) 0.1);
        Log.i("ASDF", r.getLyrics().getText());
        Log.i("SONG", song._id);
    }
}
