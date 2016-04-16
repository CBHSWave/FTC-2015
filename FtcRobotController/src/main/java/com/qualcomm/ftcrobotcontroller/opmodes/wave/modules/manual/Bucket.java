package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.BucketHard;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Scaling;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Setters;

public class Bucket implements Module {
    protected boolean lastA2 = false;
    protected boolean bucketDoorToggle = false;
    protected BucketHard hard;
    protected Scaling scaled;
    protected Setters setters;

    @Inject
    public Bucket(BucketHard hard, Scaling scaled, Setters setters) {
        this.hard = hard;
        this.scaled = scaled;
        this.setters = setters;
    }

    @Override
    public void setup(Wave mode) {
        hard.setup(mode);
    }

    @Override
    public void loop(Wave mode) {
        hard.loop(mode);
        scaled.loop(mode);
        double rightScaled = scaled.joystick(mode.gamepad2.right_trigger)/2;
        double leftScaled = scaled.joystick(mode.gamepad2.left_trigger)/2;
        double bucketRot = (rightScaled - leftScaled) + 0.5;
        setters.servos(bucketRot, hard.bucketRotationServo);

        if (mode.gamepad2.a && !lastA2) {
            bucketDoorToggle = !bucketDoorToggle;
        }

        if (bucketDoorToggle) {
            setters.servos(1, hard.bucketDoor);
        } else {
            setters.servos(0, hard.bucketDoor);
        }

        lastA2 = mode.gamepad2.a;
    }

    @Override
    public void stop(Wave mode) {

    }
}
