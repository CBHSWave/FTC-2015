package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.LiftHard;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Scaling;

public class Lift implements Module {
    protected LiftHard lift;
    protected Scaling scaled;

    @Inject
    public Lift(LiftHard lift, Scaling scaled) {
        this.lift = lift;
        this.scaled = scaled;
    }

    @Override
    public void setup(Wave mode) {
        lift.setup(mode);
    }

    @Override
    public void loop(Wave mode) {
        lift.loop(mode);
        scaled.loop(mode);
        scaled.motors(mode.gamepad2.left_stick_y, lift.liftMotor1);
        scaled.motors(mode.gamepad2.right_stick_y, lift.liftMotor2);
    }

    @Override
    public void stop(Wave mode) {

    }
}
