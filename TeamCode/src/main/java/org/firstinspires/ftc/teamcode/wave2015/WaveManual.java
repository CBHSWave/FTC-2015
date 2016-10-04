package org.firstinspires.ftc.teamcode.wave2015;

/**
 * Created by Wes
 * Manual period code
 */
public class WaveManual extends Wave2015 {
    protected boolean lastA2 = false;
    protected boolean lastRb2 = false;
    protected boolean bucketDoorToggle = false;
    protected boolean wireToggle = false;

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


        // OPTIONAL CHURRO
        float churroValue = (-gamepad1.left_trigger / 2 + gamepad1.right_trigger / 2);
        scaledMotors(churroValue, churroGrabber);

        // Gamepad 2

        double bucketRot = (scaleJoystick(gamepad2.right_trigger)/2 - scaleJoystick(gamepad2.left_trigger)/2) + 0.5;
        setServos(bucketRot, bucketRotationServo);

        if (gamepad2.a && !lastA2) {
            bucketDoorToggle = !bucketDoorToggle;
        }

        if (bucketDoorToggle) {
            setServos(1, bucketDoor);
        } else {
            setServos(0, bucketDoor);
        }

        if (gamepad2.right_bumper && !lastRb2) {
            wireToggle = !wireToggle;
        }

        if (wireToggle) {
            setMotors(1, wireMotor);
        } else {
            setMotors(0, wireMotor);
        }

        scaledMotors(gamepad2.left_stick_y, liftMotor1);
        scaledMotors(gamepad2.right_stick_y, liftMotor2);

        // Store previous values

        lastA2 = gamepad2.a;
        lastRb2 = gamepad2.right_bumper;
        super.loop();
    }
}
