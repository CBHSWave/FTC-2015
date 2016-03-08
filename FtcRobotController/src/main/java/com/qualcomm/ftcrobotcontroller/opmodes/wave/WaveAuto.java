package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import java.util.concurrent.TimeUnit;

/**
 * Created by Wes
 * Autonomous period code
 */
public class WaveAuto extends WaveTelemetry {
    @Override
    public void setup() {
        super.setup();

        setMotors(1, spinnerMotor);
    }

    @Override
    public void start() {
        super.start();

        setMotors(0.5, motorLeft, motorRight);
    }

    @Override
    public void loop() {
        long timeSeconds = TimeUnit.NANOSECONDS.toSeconds((long) time);
        long startSeconds = TimeUnit.NANOSECONDS.toSeconds( (long) startTime);
        if (timeSeconds - startSeconds == 2) {
            setMotors(0, motorLeft, motorRight);
        }
    }

    @Override
    public void stop() {
        setMotors(0, spinnerMotor);
    }
}
