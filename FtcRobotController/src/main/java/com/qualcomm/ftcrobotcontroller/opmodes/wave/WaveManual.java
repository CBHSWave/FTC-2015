package com.qualcomm.ftcrobotcontroller.opmodes.wave;

/**
 * Created by Wes
 * Manual period code
 */
public class WaveManual extends WaveTele {
    @Override
    public void loop() {
        // Gamepad 1

        if (gamepad1.right_bumper) {
            setMotors(1, spinnerMotor);
        } else if (gamepad1.left_bumper) {
            setMotors(-1, spinnerMotor);
        } else {
            setMotors(0, spinnerMotor);
        }

        float churroValue = (-gamepad1.left_trigger / 2 + gamepad1.right_trigger / 2);
        scaledMotors(churroValue, churroGrabber);

        // Gamepad 2

        if (gamepad2.dpad_up) {
            bucketRotationServo.setPosition(0);
            setServos(0, bucketRotationServo);
        } else if (gamepad2.dpad_down) {
            bucketRotationServo.setPosition(1);
            setServos(1, bucketRotationServo);
        } else {
            bucketRotationServo.setPosition(0.5);
            setServos(0.5, bucketRotationServo);
        }

        scaledMotors(gamepad2.left_stick_y, liftMotor1);
        scaledMotors(gamepad2.right_stick_y, liftMotor2);

        super.loop();
    }

}
