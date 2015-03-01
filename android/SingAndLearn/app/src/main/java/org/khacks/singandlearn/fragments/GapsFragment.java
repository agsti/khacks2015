package org.khacks.singandlearn.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.khacks.singandlearn.R;
import org.khacks.singandlearn.TestActivity;
import org.khacks.singandlearn.datastore.Word;
import org.khacks.singandlearn.datastore.WordsDatastore;

/**
 * Created by gus on 28/02/15.
 */
public class GapsFragment extends Fragment implements View.OnClickListener {
    private int correctAnswer;
    private WordsDatastore wordsDatastore;
    private int numWords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gaps_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordsDatastore = new WordsDatastore(getActivity());
        numWords =0;
    }


    public void clear(){
        numWords =0;
    }

    public void putWord(Word[] words){


        switch(numWords) {
            case 0:
                LinearLayout ll = (LinearLayout) getView();
                Button b1 = (Button) ll.findViewById(R.id.button1);
                b1.setText(words[0].getWord());
                b1.setOnClickListener(this);
                b1.setTag(words[0]);
            break;

            case 1:
            Button b2 = (Button) ll.findViewById(R.id.button2);
            b2.setText(words[1].getWord());
            b2.setOnClickListener(this);
            b2.setTag(words[1]);
            break;

            case 2:
            Button b3 = (Button) ll.findViewById(R.id.button3);
            b3.setText(words[2].getWord());
            b3.setOnClickListener(this);
            b3.setTag(words[2]);
            break;

            case 3:
            Button b4 = (Button) ll.findViewById(R.id.button4);
            b4.setText(words[3].getWord());
            b4.setOnClickListener(this);
            b4.setTag(words[3]);
            break;
            default:
                Log.wtf("YOU ARE FUCKED", "PRETTY MUCH");

        }
        numWords++;

    }




    public void setCorrectAnswer(int index){
        correctAnswer = index;
    }

    @Override
    public void onClick(View v) {
        ViewGroup mainView = (ViewGroup)getView();
        boolean isCorrectAnswer = (mainView.getChildAt(correctAnswer) == v);

        ((TestActivity)getActivity()).publishScore(isCorrectAnswer);
            // we have clicked a correct answer

    }
}
