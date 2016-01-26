package com.qualcomm.ftcrobotcontroller.opmodes.wave;

/**
 * Created by Wes
 * Manual period code
 */
public class WaveManual extends WaveTele {
    protected boolean lastA2 = false;
    protected boolean bucketDoorToggle = false;

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
            setServos(0, bucketRotationServo);
        } else if (gamepad2.dpad_down) {
            setServos(1, bucketRotationServo);
        } else {
            setServos(0.5, bucketRotationServo);
        }

        if (gamepad2.a && !lastA2) {
            bucketDoorToggle = !bucketDoorToggle;
        }

        if (bucketDoorToggle) {
            setServos(1, bucketDoor);
        } else {
            setServos(0, bucketDoor);
        }

        scaledMotors(gamepad2.left_stick_y, liftMotor1);
        scaledMotors(gamepad2.right_stick_y, liftMotor2);

        // Store previous values

        lastA2 = gamepad2.a;
        super.loop();
    }
}
