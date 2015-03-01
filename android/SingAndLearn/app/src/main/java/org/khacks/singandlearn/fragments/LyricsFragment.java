package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.khacks.singandlearn.R;

/**
 * Created by gus on 28/02/15.
 */
public class LyricsFragment extends Fragment {

    private  SpannableStringBuilder style;
    private ForegroundColorSpan color = new ForegroundColorSpan (Color.parseColor("#1E88E5"));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.lyrics, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        style = new SpannableStringBuilder("RANDOMSTRING");
    }



    public void setLyricsHighlight( int end){
        style.setSpan(color,0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ((TextView)getView()).setText(style);
    }


    public void setLyrics(String lyric){
        TextView view = (TextView)getView();
        style.clear();
        style.append(lyric);
        view.setText(style);


    }






}
