package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTele;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.Wheels;

public class Drive implements Module<WaveTele> {
    protected Wheels wheels;

    @Inject
    public Drive(Wheels wheels) {
        this.wheels = wheels;
    }

    @Override
    public void setup(WaveTele mode) {
        wheels.setup(mode);
    }

    @Override
    public void loop(WaveTele mode) {
        wheels.loop(mode);
        mode.scaledMotors(mode.gamepad1.right_stick_y, wheels.motorRight);
        mode.scaledMotors(mode.gamepad1.left_stick_y, wheels.motorLeft);
    }
}
