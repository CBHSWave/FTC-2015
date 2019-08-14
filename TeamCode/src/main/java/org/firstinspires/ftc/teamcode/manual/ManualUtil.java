package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.general.GeneralUtil;

/**
 * This class should handle common Manual OpMode utilities
 * Created by wjackson on 9/22/2017.
 */

public class ManualUtil {

    public static double pythag(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Method for scaling inputs to the motors from the gamepad to allow for more natural controls
     * Gradient for more natural controls on joysticks and other double-valued gamepad inputs
     * Given by FTC
     * @param value value to scale
     * @return the scaled value
     */
    public static double scale(double value) {
        // Defines the gradient on the scaling
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

    /**
     * Tank Drive code in a general sense
     * Assigns all left motors to the left stick's y-value and all right motors the right stick's
     * y-value
     * @param pad gamepad to use
     * @param leftmotors all the left motors
     * @param rightmotors all the right motors
     */
    public static void normalDriveGeneral(Gamepad pad, DcMotor[] leftmotors, DcMotor[] rightmotors) {
        // Loop through the motors and set values
        for (DcMotor left : leftmotors) {
            left.setPower(scale(pad.left_stick_y));
        }

        // Loop through the motors and set values
        for (DcMotor right : rightmotors) {
            right.setPower(scale(pad.right_stick_y));
        }
    }

    /**
     * Tank Drive Code for two motors
     * @param pad gamepad to use
     * @param leftmotor the left motor
     * @param rightmotor the right motor
     */
    public static void normalDrive(Gamepad pad, DcMotor leftmotor, DcMotor rightmotor) {
        // Call into the general normal drive
        normalDriveGeneral(pad, new DcMotor[]{leftmotor}, new DcMotor[]{rightmotor});
    }

    /**
     * Mecanum Drive code
     * Left stick controls movement in any direction
     * Right stick x-value controls rotation on a zero-turn center
     * @param pad gamepad to use
     * @param threshold threshold to begin rotating
     * @param frontleft the motor in the front left position
     * @param frontright the motor in the front right position
     * @param backleft the motor in the back left position
     * @param backright the motor in the back right position
     */
    public static void mecanumDrive(Gamepad pad, double threshold,
                                    DcMotor frontleft, DcMotor frontright,
                                    DcMotor backleft, DcMotor backright) {
        // {flPow, frPow, blPow, brPow}
        double[] powers = GeneralUtil.cartesianMecanum(pad.left_stick_x, pad.left_stick_y);
//        double flPow = pad.left_stick_y - pad.left_stick_x;
//        double frPow = pad.left_stick_y + pad.left_stick_x;
//        double blPow = pad.left_stick_y + pad.left_stick_x;
//        double brPow = pad.left_stick_y - pad.left_stick_x;

        // Scale the values, half them, then set the power
        frontleft.setPower(scale(powers[0]));
        frontright.setPower(scale(powers[1]));
        backleft.setPower(scale(powers[2]));
        backright.setPower(scale(powers[3]));

        // Check if we've hit the threshold then rotate
        if (pad.right_stick_x > threshold || pad.right_stick_x < -threshold) {
            frontleft.setPower(-pad.right_stick_x);
            frontright.setPower(pad.right_stick_x);
            backleft.setPower(-pad.right_stick_x);
            backright.setPower(pad.right_stick_x);
        }
    }

    /**
     * This method handles all the drive modes that we have available. It relies on a null
     * check on the HardwareBot class
     * @param robot the hardware we are using
     * @param pad the gamepad the drive controls should be on
     */
    public static void drive(HardwareBot robot, Gamepad pad) {
        // Null check
        if (robot.fr.isPresent() && robot.fl.isPresent() && robot.bl.isPresent() && robot.br.isPresent()) {
            // Call into the mecanum drive code with a 0.1 threshold
            mecanumDrive(pad, 0.1,
                    robot.fl.get(), robot.fr.get(),
                    robot.bl.get(), robot.br.get());
        }

        // Null check
        if (robot.leftMotor.isPresent() && robot.rightMotor.isPresent()) {
            // Call into the tank drive code
            normalDrive(pad, robot.leftMotor.get(), robot.rightMotor.get());
        }
    }

}