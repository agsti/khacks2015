package org.khacks.singandlearn;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;

import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;

/**
 * Created by gus on 28/02/15.
 */
public class TestActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private Song song;

    public static final String SONG_ID = "SONG_ID";


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        String songId = getIntent().getExtras().getString(SONG_ID);

        SongsDatastore datastore = new SongsDatastore(this);
        song = datastore.getSong(songId);

        Uri songUri = Uri.parse(song.fileName);
        mediaPlayer = MediaPlayer.create(this, songUri);


    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
