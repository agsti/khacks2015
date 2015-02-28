package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gus on 28/02/15.
 */
public class LyricsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new TextView(getActivity());
    }

    void setLyrics(String lyric){
        TextView view = (TextView)getView();
        view.setText(lyric);
    }


}
