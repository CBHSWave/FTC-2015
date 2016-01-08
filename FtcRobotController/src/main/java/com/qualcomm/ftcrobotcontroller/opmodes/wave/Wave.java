package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Wes
 * Top Level class for all bot stuff, automatically loops
 */
public abstract class Wave extends OpMode {
    protected Thread loopThread;
    protected boolean running;

    /**
     * Use to setup everything before entering loop
     */
    protected abstract void setup();

    @Override
    public void init() {
        setup();
        loopThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running)
                    loop();
            }
        });
        loopThread.run();
    }
}
