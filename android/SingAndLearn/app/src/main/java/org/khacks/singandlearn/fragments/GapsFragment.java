package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import org.khacks.singandlearn.R;
import org.khacks.singandlearn.datastore.Word;
import org.khacks.singandlearn.datastore.WordsDatastore;

/**
 * Created by gus on 28/02/15.
 */
public class GapsFragment extends Fragment implements View.OnClickListener {
    private int correctAnswer;
    private WordsDatastore wordsDatastore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gaps_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordsDatastore = new WordsDatastore(getActivity());
    }


    public void clear(){
        ((ViewGroup)getView()).removeAllViews();
    }

    public void putWord(Word word){
        GridLayout layout = (GridLayout)getView();

        LayoutInflater inflater = LayoutInflater.from(getActivity());


        TextView view = (TextView) inflater.inflate(R.layout.word_layout, (ViewGroup) getView());
        view.setOnClickListener(this);
        view.setText(word.getWord());
        view.setTag(word);

    }

    public void setCorrectAnswer(int index){
        correctAnswer = index;
    }

    @Override
    public void onClick(View v) {
        ViewGroup mainView = (ViewGroup)getView();
        if(mainView.getChildAt(correctAnswer) == v){
            // we have clicked a correct answer
            wordsDatastore.saveAttempt((Word) v.getTag(), true);
        }
        else{
            wordsDatastore.saveAttempt((Word) v.getTag(), false);
        }
    }
}
