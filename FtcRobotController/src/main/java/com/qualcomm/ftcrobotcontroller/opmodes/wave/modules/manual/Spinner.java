package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual;

import com.google.inject.Inject;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTele;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware.SpinnerHard;

public class Spinner implements Module<WaveTele> {
    protected boolean lastRb2 = false;
    protected boolean wireToggle = false;
    protected SpinnerHard spinner;

    @Inject
    public Spinner(SpinnerHard spinner) {
        this.spinner = spinner;
    }

    @Override
    public void setup(WaveTele mode) {
        spinner.setup(mode);
    }

    @Override
    public void loop(WaveTele mode) {
        spinner.loop(mode);
        // Gamepad 1

        if (mode.gamepad1.right_bumper) {
            mode.setMotors(1, spinner.spinnerMotor);
        } else if (mode.gamepad1.left_bumper) {
            mode.setMotors(-1, spinner.spinnerMotor);
        } else {
            mode.setMotors(0, spinner.spinnerMotor);
        }


        // OPTIONAL CHURRO

        float churroValue = (-mode.gamepad1.left_trigger / 2 + mode.gamepad1.right_trigger / 2);
        mode.scaledMotors(churroValue, spinner.churroGrabber);

        // Gamepad 2

        if (mode.gamepad2.right_bumper && !lastRb2) {
            wireToggle = !wireToggle;
        }

        if (wireToggle) {
            mode.setMotors(1, spinner.wireMotor);
        } else {
            mode.setMotors(0, spinner.wireMotor);
        }



        // Store previous values

        lastRb2 = mode.gamepad2.right_bumper;
    }
}
