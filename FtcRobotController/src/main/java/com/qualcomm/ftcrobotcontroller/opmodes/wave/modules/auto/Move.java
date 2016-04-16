package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.auto;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.Wheels;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Setters;

import java.util.concurrent.TimeUnit;

public class Move implements Module {
    protected Wheels wheels;
    protected Setters setters;

    @Inject
    public Move(Wheels wheels, Setters setters) {
        this.wheels = wheels;
        this.setters = setters;
    }

    @Override
    public void setup(Wave mode) {
        wheels.setup(mode);
        setters.motors(0.5, wheels.motorLeft, wheels.motorRight);
    }

    @Override
    public void loop(Wave mode) {
        setters.loop(mode);
        wheels.loop(mode);
        long timeSeconds = TimeUnit.NANOSECONDS.toSeconds((long) mode.time);
        long startSeconds = TimeUnit.NANOSECONDS.toSeconds( (long) mode.startTime);

        if (timeSeconds - startSeconds >= 2) {
            setters.motors(0, wheels.motorLeft, wheels.motorRight);
        }
    }

    @Override
    public void stop(Wave mode) {

    }
}
