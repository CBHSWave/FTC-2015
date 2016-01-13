package com.qualcomm.ftcrobotcontroller.opmodes.wave;

/**
 * Created by Wes
 * Manual period code
 */
public class WaveManual extends WaveTele {
    @Override
    public void loop() {
        joystickMotors(gamepad2.left_stick_y, liftMotor1);
        joystickMotors(gamepad2.right_stick_y, liftMotor2);

        if (gamepad1.right_bumper) {
            setMotors(1, spinnerMotor);
        } else if (gamepad1.left_bumper) {
            setMotors(-1, spinnerMotor);
        } else {
            setMotors(0, spinnerMotor);
        }

        super.loop();
    }

}
