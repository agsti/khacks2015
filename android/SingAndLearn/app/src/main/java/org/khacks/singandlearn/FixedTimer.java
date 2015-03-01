package org.khacks.singandlearn;

import android.os.Handler;

/**
 * Created by gus on 01/03/15.
 */
public class FixedTimer {

    private Handler handler;
    private Runnable runnable;
    private int delay;
    private boolean shouldRun;

    public FixedTimer(Handler handler, Runnable runnable, int delay) {
        this.handler = handler;
        this.runnable = runnable;
        this.delay = delay;
        shouldRun = true;
    }

    public void start(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(shouldRun) {
                    runnable.run();
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
    }

    public void stop (){
        shouldRun = false;
    }
}
