package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.auto;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.SpinnerHard;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Setters;

public class Spin implements Module {
    protected SpinnerHard hard;
    protected Setters setters;

    @Inject
    public Spin(SpinnerHard hard, Setters setters) {
        this.hard = hard;
        this.setters = setters;
    }

    @Override
    public void setup(Wave mode) {
        hard.setup(mode);
        setters.motors(1, hard.spinnerMotor);
    }

    @Override
    public void loop(Wave mode) {
        hard.loop(mode);
        setters.loop(mode);
    }

    @Override
    public void stop(Wave mode) {
        setters.motors(0, hard.spinnerMotor);
    }
}
