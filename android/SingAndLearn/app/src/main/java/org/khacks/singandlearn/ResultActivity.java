package org.khacks.singandlearn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by gus on 28/02/15.
 */
public class ResultActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ResultActivity", "oncreate");
        setContentView(R.layout.result_act);


    }
}
