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
public class TestSongsDatabase extends AndroidTestCase {
    private SongsDatastore songsDatastore;
    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        songsDatastore = new SongsDatastore(context);
    }
    public void testGetEntries() {
        SingToLearnOpenHelper.getInstance(getContext()).onSetup();
        assertEquals(songsDatastore.getAllSongs().getCount(), 5);
        Song song = songsDatastore.getSong("LY6be37788-bfb7-11e4-a9a8-20c9d0465fa9");
        Log.i("SONG", song._id);
    }
}