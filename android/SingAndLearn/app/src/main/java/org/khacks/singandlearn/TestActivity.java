package org.khacks.singandlearn;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;
import org.khacks.singandlearn.fragments.GapsFragment;
import org.khacks.singandlearn.fragments.LyricsFragment;
import org.khacks.singandlearn.fragments.MediaPlayerFragment;

import java.io.IOException;

/**
 * Created by gus on 28/02/15.
 */
public class TestActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private Song song;

    GapsFragment gapsFragment;
    LyricsFragment lyricsFragment;
    MediaPlayerFragment mediaPlayerFragment;

    Runnable nextParagraph = new Runnable() {
        @Override
        public void run() {
            gapsFragment.setCorrectAnswer(2);
//            gapsFragment.setWords(new String[]{"Hello", "Bye", "Correct", "Wrong"});

            lyricsFragment.setLyrics("");

//            mediaPlayerFragment.setGoodScore();
//            mediaPlayerFragment.setWrongScore();
//            mediaPlayerFragment.setRewindPoint(mediaPlayer.getCurrentPosition());

            // then schedule for the next ti

        }
    };

    public static final String SONG_ID = "SONG_ID";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("testActivity", "onCreate");
        setContentView(R.layout.test_layout);

        gapsFragment = (GapsFragment) getFragmentManager().findFragmentById(R.id.gaps);
        lyricsFragment = (LyricsFragment) getFragmentManager().findFragmentById(R.id.lyrics);
        mediaPlayerFragment = (MediaPlayerFragment) getFragmentManager().findFragmentById(R.id.media_player);


        String songId = getIntent().getExtras().getString(SONG_ID);

        SongsDatastore datastore = new SongsDatastore(this);
        song = datastore.getSong(songId);

        try {
            Log.i("assets", getAssets().list(".")[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.parse("android.resource://org.khacks.singandlearn/raw/"+song.fileName);

        mediaPlayer = MediaPlayer.create(this, song);
        mediaPlayerFragment.setRewindPoint(0);
        mediaPlayer.start();





    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
