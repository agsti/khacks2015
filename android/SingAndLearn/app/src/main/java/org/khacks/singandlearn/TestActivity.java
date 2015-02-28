package org.khacks.singandlearn;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by gus on 28/02/15.
 */
public class TestActivity extends Activity {

    private MediaPlayer mediaPlayer;
    public static final String SONG_FILE_KEY = "SONG_FILE";
    public static final String SONG_ARTIST_KEY = "SONG_ARTIST";
    public static final String SONG_NAME_KEY = "SONG_NAME";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent comingIntent = getIntent();


        Bundle extras = comingIntent.getExtras();

        String songFile = extras.getString(SONG_FILE_KEY);
        String songArtist = extras.getString(SONG_ARTIST_KEY);

        Uri songUri = Uri.parse(songFile);
        mediaPlayer = MediaPlayer.create(this, songUri);
        

    }
}
