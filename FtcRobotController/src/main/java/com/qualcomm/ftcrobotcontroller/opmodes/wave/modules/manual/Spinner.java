package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.SpinnerHard;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Scaling;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Setters;

public class Spinner implements Module {
    protected boolean lastRb2 = false;
    protected boolean wireToggle = false;
    protected SpinnerHard spinner;
    protected Scaling scaled;
    protected Setters setters;

    @Inject
    public Spinner(SpinnerHard spinner, Scaling scaled, Setters setters) {
        this.spinner = spinner;
        this.scaled = scaled;
        this.setters = setters;
    }

    @Override
    public void setup(Wave mode) {
        spinner.setup(mode);
    }

    @Override
    public void loop(Wave mode) {
        spinner.loop(mode);
        scaled.loop(mode);

        // Gamepad 1

        if (mode.gamepad1.right_bumper) {
            setters.motors(1, spinner.spinnerMotor);
        } else if (mode.gamepad1.left_bumper) {
            setters.motors(-1, spinner.spinnerMotor);
        } else {
            setters.motors(0, spinner.spinnerMotor);
        }


        // OPTIONAL CHURRO

        float churroValue = (-mode.gamepad1.left_trigger / 2 + mode.gamepad1.right_trigger / 2);
        scaled.motors(churroValue, spinner.churroGrabber);

        // Gamepad 2

        if (mode.gamepad2.right_bumper && !lastRb2) {
            wireToggle = !wireToggle;
        }

        if (wireToggle) {
            setters.motors(1, spinner.wireMotor);
        } else {
            setters.motors(0, spinner.wireMotor);
        }



        // Store previous values

        lastRb2 = mode.gamepad2.right_bumper;
    }

    @Override
    public void stop(Wave mode) {

    }
}
