package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.Wheels;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Scaling;

public class Drive implements Module {
    protected Wheels wheels;
    protected Scaling scaled;

    @Inject
    public Drive(Wheels wheels, Scaling scaled) {
        this.wheels = wheels;
        this.scaled = scaled;
    }

    @Override
    public void setup(Wave mode) {
        wheels.setup(mode);
    }

    @Override
    public void loop(Wave mode) {
        scaled.loop(mode);
        wheels.loop(mode);
        scaled.motors(mode.gamepad1.right_stick_y, wheels.motorRight);
        scaled.motors(mode.gamepad1.left_stick_y, wheels.motorLeft);
    }

    @Override
    public void stop(Wave mode) {

    }
}
