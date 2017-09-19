package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.HashMap;

/**
 * Created by wjackson on 9/18/2017.
 * This class is used to contain utilities which are useful to all Autonomous modes
 */

public class AutoUtil {

    // These are the variables which are needed for the AutoUtil class to do its work
    private final double TICKS_PER_CM;
    private final double RADIUS;
    private final double CIRC;
    private final LinearOpMode OP_MODE;

    // This constructor will allow you to access the AutoUtil class and set the variables it needs
    public AutoUtil(double TICKS_PER_CM, double RADIUS, LinearOpMode OP_MODE) {
        this.TICKS_PER_CM = TICKS_PER_CM;
        this.RADIUS = RADIUS;
        this.OP_MODE = OP_MODE;
        this.CIRC = 2 * Math.PI * this.RADIUS;
    }

    // This static method provides a default setup for the test bot's AutoUtil
    public static AutoUtil testBot(LinearOpMode OP_MODE) {
        return new AutoUtil((23.33233 + 25.24073)/2, 23, OP_MODE);
    }

    // This method goes forward for a specified distance in cm on the specified motors
    public void goForward(double cm, DcMotor... motors) {

        // Establish the HashMaps we will use to invoke the goDistanceMap function
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();

        // For each motor that was specified by the caller...
        for (DcMotor motor : motors) {
            // Put the power and the centimeters needed into the respective HashMaps
            powers.put(motor, Float.valueOf(1));
            cms.put(motor, cm);
        }

        // Finally, call the goDistanceMap function with those HashMaps
        goDistanceMap(powers, cms);
    }

    public void zeroTurn(DcMotor left, DcMotor right, double angle) {

        // Establish the HashMaps we will use to invoke the goDistanceMap function
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();

        // Set up the powers with equal and opposite for zero-turning affect
        powers.put(left, Float.valueOf(angle < 0 ? 1 : -1));
        powers.put(right, Float.valueOf(angle < 0 ? -1 : 1));

        // Convert the angles into a distance in centimeters to use
        cms.put(left, angleToDistance(Math.abs(angle)));
        cms.put(right, angleToDistance(Math.abs(angle)));

        // Invoke the goDistanceMap function
        goDistanceMap(powers, cms);
    }

    public void goDistanceMap(HashMap<DcMotor, Float> motors, HashMap<DcMotor, Double> cm) {

        // Create a new HashMap to store the starting positions
        HashMap<DcMotor, Double> starts = new HashMap<>();

        // Loop through all the available motors specified and...
        for (DcMotor motor : motors.keySet()) {

            // Set the appropriate power and store the starting positions in starts
            motor.setPower(motors.get(motor));
            starts.put(motor, traveled(motor));
        }

        // While the OpMode remains active and the there are motors (starts) remaining...
        while (OP_MODE.opModeIsActive() && !starts.isEmpty()) {

            // Loop through each motor...
            for (DcMotor motor : motors.keySet()) {

                // If the starts still has this motor and it has traveled the distance it needs to...
                if (starts.containsKey(motor) && Math.abs(traveled(motor) - starts.get(motor)) > cm.get(motor)) {

                    // Turn the motor off and remove it from starts
                    motor.setPower(0);
                    starts.remove(motor);
                }
            }
        }
    }

    // Return centimeters traveled since start
    public double traveled(DcMotor motor) {
        return convertEncoder(motor.getCurrentPosition());
    }

    // Convert envoder ticks into centimeters
    public double convertEncoder(float encoderTicks) {
        return encoderTicks / TICKS_PER_CM;
    }

    // Convert a turned angle into the centimeters traveled by a wheel
    public double angleToDistance(double angle) {
        return angle / 360 * CIRC;
    }
}
