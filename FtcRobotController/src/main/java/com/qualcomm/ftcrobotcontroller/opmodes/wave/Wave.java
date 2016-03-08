package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Wes
 * Top Level class for all bot stuff, automatically loops
 */
public abstract class Wave extends OpMode {
    protected double startTime;

    /**
     * Use to setup everything before entering loop
     */
    protected abstract void setup();

    @Override
    public void start() {
        startTime = time;
    }

    @Override
    public void init() {
        setup();
    }
}
