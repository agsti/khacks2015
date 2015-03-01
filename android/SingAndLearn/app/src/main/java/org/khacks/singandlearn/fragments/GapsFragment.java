package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import org.khacks.singandlearn.R;

/**
 * Created by gus on 28/02/15.
 */
public class GapsFragment extends Fragment implements View.OnClickListener {
    private int correctAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gaps_main, container, false);
    }

    public void setWords(String[] words){
        GridLayout layout = (GridLayout)getView();
        layout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        for(String word : words){
            inflater.inflate(R.layout.word_layout, (ViewGroup) getView()).setOnClickListener(this);
        }
    }

    public void setCorrectAnswer(int index){
        correctAnswer = index;
    }

    @Override
    public void onClick(View v) {
        ViewGroup mainView = (ViewGroup)getView();
        if(mainView.getChildAt(correctAnswer) == v){
            // we have clicked a correct answer
        }
        else{

        }
    }
}
