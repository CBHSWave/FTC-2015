package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.google.inject.Injector;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.auto.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wes
 * Autonomous period code
 */
public class WaveAuto extends WaveTelemetry {
    public WaveAuto(Injector injector) {
        super(injector);
    }

    @Override
    public void init() {
        super.init();

        // setMotors(1, spinner.spinnerMotor);
    }

    @Override
    public List<Class<? extends Module>> getModules() {
        ArrayList<Class<? extends Module>> mods = new ArrayList<Class<? extends Module>>();
        mods.add(Move.class);
        return mods;
    }

    @Override
    public void start() {
        super.start();

        // setMotors(0.5, wheels.motorLeft, wheels.motorRight);
        // setMotors(1, spinner.spinnerMotor);
        super.start();
    }

    @Override
    public void loop() {
        long timeSeconds = TimeUnit.NANOSECONDS.toSeconds((long) time);
        long startSeconds = TimeUnit.NANOSECONDS.toSeconds( (long) startTime);
        if (timeSeconds - startSeconds == 2) {
            // setMotors(0, wheels.motorLeft, wheels.motorRight);
        }
        super.loop();
    }

    @Override
    public void stop() {
        // setMotors(0, spinner.spinnerMotor);
    }
}
