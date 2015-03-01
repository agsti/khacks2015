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

/**
 * Created by gus on 28/02/15.
 */
public class LyricsFragment extends Fragment {

    private  SpannableStringBuilder style;
    private ForegroundColorSpan color = new ForegroundColorSpan (Color.RED);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("HERE GOES THE LYRICS");
        style = new SpannableStringBuilder("RANDOMSTRING");
        return tv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
