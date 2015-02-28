package org.khacks.singandlearn.utils;

import android.os.Handler;

/**
 * Created by gus on 28/02/15.
 */
public class FixedTimer extends Handler implements Runnable {
    private final int msec;
    private final Runnable runnable;
    private boolean shouldStop;
    public FixedTimer(int msec, Runnable r){
        this.msec = msec;
        postDelayed(this, msec);
        runnable = r;
        shouldStop = false;
    }

    public void stop(){
        shouldStop = true;
    }

    @Override
    public void run() {
        runnable.run();

        if(!shouldStop)
            postDelayed(this, msec);

    }
}
