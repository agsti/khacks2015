package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.khacks.singandlearn.R;
import org.khacks.singandlearn.TestActivity;

import java.io.IOException;

/**
 * Created by gus on 28/02/15.
 */
public class MediaPlayerFragment extends Fragment {

    TextView songTV, artistTV, goodTV, wrongTV;
    ImageButton rewindBTN;
    private MediaPlayer mediaPlayer;
    private int rewindPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.media_player, container, false);
        songTV = (TextView) mainView.findViewById(R.id.song_n);
        artistTV = (TextView) mainView.findViewById(R.id.song_a);
        goodTV = (TextView) mainView.findViewById(R.id.good_score);
        wrongTV = (TextView) mainView.findViewById(R.id.wrong_score);
        rewindBTN = (ImageButton) mainView.findViewById(R.id.playpause_btn);
        rewindBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // when should we pause?
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.seekTo(rewindPoint);

            }
        });
        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mediaPlayer = ((TestActivity)getActivity()).getMediaPlayer();
    }

    public void setGoodScore(int goodScore){
        goodTV.setText(String.valueOf(goodScore));
    }

    public void setWrongScore(int badScore){
        wrongTV.setText(String.valueOf(badScore));
    }

    public void setRewindPoint(int rewindPoint){
        this.rewindPoint = rewindPoint;
    }


}