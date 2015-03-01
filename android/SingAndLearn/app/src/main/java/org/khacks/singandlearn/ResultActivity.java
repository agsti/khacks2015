package org.khacks.singandlearn;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

/**
 * Created by gus on 28/02/15.
 */
public class ResultActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_act);

        //Get correct & incorrect
        //WordsDatastore wds = new WordsDatastore();
        //test value
        int correct = 10;
        int incorrect = 4;

        PieGraph graph = (PieGraph)findViewById(R.id.graph);
        addSlice(graph, Color.GREEN, correct);
        addSlice(graph, Color.RED, incorrect);
    }

    private void addSlice(PieGraph graph, int color, int value) {
        PieSlice slice = new PieSlice();
        slice.setColor(color);
        slice.setValue(value);
        graph.addSlice(slice);
    }
}
