package org.firstinspires.ftc.teamcode.wave;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Wes
 * Contains the joystick movement code
 */
@TeleOp(name= "Basic Wave Tele", group = "Wave")
public class WaveTele extends WaveHardware {
    @Override
    public void loop() {
        scaledMotors(gamepad1.right_stick_y, motorRight);
        scaledMotors(gamepad1.left_stick_y, motorLeft);

        // Use BotTelemetry loop to log data
        super.loop();
    }

    protected void scaledMotors(float inputValue, DcMotor... motors) {
        // clip the values so that the values never exceed +/- 1
        float value = Range.clip(inputValue, -1, 1);

        // scale the value to make it easier to control
        // the robot more precisely at slower speeds.
        value = (float)scaleJoystick(value);

        // write the values to the motors
        setMotors(value, motors);
    }

    protected void scaledServos(double inputValue, Servo... servos) {
        double value = Range.clip(inputValue, 0, 1);
        value = scaleJoystick(value);
        setServos(value, servos);
    }

    /*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
    protected double scaleJoystick(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
