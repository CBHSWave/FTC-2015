package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by wjackson on 9/22/2017.
 */

public class ManualUtil {

    // Method for scaling inputs to the motors from the gamepad to allow for more natural controls
    public static double scale(double value) {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (value * 16.0);

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
        if (value < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

    public static void normalDriveGeneral(Gamepad pad, DcMotor[] leftmotors, DcMotor[] rightmotors) {
        for (DcMotor left : leftmotors) {
            left.setPower(scale(pad.left_stick_y));
        }

        for (DcMotor right : rightmotors) {
            right.setPower(scale(pad.right_stick_y));
        }
    }

    public static void normalDrive(Gamepad pad, DcMotor leftmotor, DcMotor rightmotor) {
        leftmotor.setPower(scale(pad.left_stick_y));
        rightmotor.setPower(scale(pad.right_stick_y));
    }

    public static void mecanumDrive(Gamepad pad, double threshhold,
                                    DcMotor frontleft, DcMotor frontright,
                                    DcMotor backleft, DcMotor backright) {
        double flPow = -pad.left_stick_y - pad.left_stick_x;
        double frPow = pad.left_stick_y - pad.left_stick_x;
        double blPow = pad.left_stick_y - pad.left_stick_x;
        double brPow = -pad.left_stick_y - pad.left_stick_x;

        frontleft.setPower(scale(flPow) / 2);
        frontright.setPower(scale(frPow) / 2);
        backleft.setPower(scale(blPow) / 2);
        backright.setPower(scale(brPow) / 2);

        if (pad.right_stick_x > threshhold || pad.right_stick_x < -threshhold) {
            frontleft.setPower(pad.right_stick_x);
            frontright.setPower(pad.right_stick_x);
            backleft.setPower(-pad.right_stick_x);
            backright.setPower(-pad.right_stick_x);
        }
    }
}