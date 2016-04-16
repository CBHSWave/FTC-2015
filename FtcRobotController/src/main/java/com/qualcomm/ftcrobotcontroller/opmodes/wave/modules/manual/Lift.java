package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTele;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.LiftHard;

public class Lift implements Module<WaveTele> {
    protected LiftHard lift;

    @Inject
    public Lift(LiftHard lift) {
        this.lift = lift;
    }

    @Override
    public void setup(WaveTele mode) {
        lift.setup(mode);
    }

    @Override
    public void loop(WaveTele mode) {
        lift.loop(mode);
        mode.scaledMotors(mode.gamepad2.left_stick_y, lift.liftMotor1);
        mode.scaledMotors(mode.gamepad2.right_stick_y, lift.liftMotor2);
    }
}
