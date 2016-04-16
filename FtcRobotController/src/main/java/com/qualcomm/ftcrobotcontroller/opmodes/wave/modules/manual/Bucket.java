package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTele;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.BucketHard;

public class Bucket implements Module<WaveTele> {
    protected boolean lastA2 = false;
    protected boolean bucketDoorToggle = false;
    protected BucketHard hard;

    @Inject
    public Bucket(BucketHard hard) {
        this.hard = hard;
    }

    @Override
    public void setup(WaveTele mode) {
        hard.setup(mode);
    }

    @Override
    public void loop(WaveTele mode) {
        hard.loop(mode);
        double bucketRot = (mode.scaleJoystick(mode.gamepad2.right_trigger)/2 - mode.scaleJoystick(mode.gamepad2.left_trigger)/2) + 0.5;
        mode.setServos(bucketRot, hard.bucketRotationServo);

        if (mode.gamepad2.a && !lastA2) {
            bucketDoorToggle = !bucketDoorToggle;
        }

        if (bucketDoorToggle) {
            mode.setServos(1, hard.bucketDoor);
        } else {
            mode.setServos(0, hard.bucketDoor);
        }

        lastA2 = mode.gamepad2.a;
    }
}
