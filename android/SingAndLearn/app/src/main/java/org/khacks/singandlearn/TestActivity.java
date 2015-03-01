package org.khacks.singandlearn;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;

import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;
import org.khacks.singandlearn.fragments.GapsFragment;
import org.khacks.singandlearn.fragments.LyricsFragment;
import org.khacks.singandlearn.fragments.MediaPlayerFragment;

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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.result_act);

        gapsFragment = (GapsFragment) getFragmentManager().findFragmentById(R.id.gaps);
        lyricsFragment = (LyricsFragment) getFragmentManager().findFragmentById(R.id.lyrics);
        mediaPlayerFragment = (MediaPlayerFragment) getFragmentManager().findFragmentById(R.id.media_player);


        String songId = getIntent().getExtras().getString(SONG_ID);

        SongsDatastore datastore = new SongsDatastore(this);
        song = datastore.getSong(songId);

        Uri songUri = Uri.parse(song.fileName);
        mediaPlayer = MediaPlayer.create(this, songUri);
        mediaPlayerFragment.setRewindPoint(0);





    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
