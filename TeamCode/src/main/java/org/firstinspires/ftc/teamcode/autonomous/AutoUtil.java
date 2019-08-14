package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.manual.HardwareBot;
import org.firstinspires.ftc.teamcode.general.Call;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjackson on 9/18/2017.
 * This class is used to contain utilities which are useful to all Autonomous modes
 */

public class AutoUtil {

    // These are the variables which are needed for the AutoUtil class to do its work
    private final double TICKS_PER_CM;
    private final double RADIUS;
    private final double CIRC;
    private final OpMode OP_MODE;

    // This constructor will allow you to access the AutoUtil class and set the variables it needs
    public AutoUtil(double TICKS_PER_CM, double RADIUS, OpMode OP_MODE) {
        this.TICKS_PER_CM = TICKS_PER_CM;
        this.RADIUS = RADIUS;
        this.OP_MODE = OP_MODE;
        this.CIRC = 2 * Math.PI * this.RADIUS;
    }

    // This static method provides a default setup for the test bot's AutoUtil
    public static AutoUtil testBot(LinearOpMode OP_MODE) {
        return new AutoUtil((23.33233 + 25.24073)/2, 15.24/2, OP_MODE);
    }

    public static void beforeAfter(Call before, Call after, ElapsedTime period, double milli) {
        beforeAfterDuring(before, after, Call.empty, period, milli);
    }

    public static void during(Call during, ElapsedTime period, double milli) {
        beforeAfterDuring(Call.empty, Call.empty, during, period, milli);
    }

    public static void beforeAfterDuring(Call before, Call after, Call during, ElapsedTime period, double milli) {
        before.run();
        double initial = period.milliseconds();
        while (period.time(TimeUnit.MILLISECONDS) < milli + initial) {
            during.run();
        }
        after.run();
    }

    public static Call setMotorsCall(final double pow, final DcMotor[] motors) {
        return new Call() {
            @Override
            public void run() {
                setMotors(pow, motors);
            }
        };
    }

    public static Call setMotorsCall (final double[] pows, final DcMotor[] motors) {
        return new Call() {

            @Override
            public void run() {
                setMotors(pows, motors);
            }
        };
    }

    public static void setMotors (final double[] pows, DcMotor[] motors) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(pows[i]);
        }
    }

    public static void setMotors (double pow, DcMotor[] motors) {
        for (DcMotor motor : motors) {
            motor.setPower(pow);
        }
    }

    public static void stopMotors(DcMotor[] motors) {
        setMotors(0, motors);
    }

    public static void resetAll(HardwareBot robot) {
        for (DcMotor motor : robot.motors) {
            motor.setPower(0);
            motor.resetDeviceConfigurationForOpMode();
        }

        for (Servo servo : robot.servos) {
            servo.setPosition(0);
        }
    }

    // This method goes forward for a specified distance in cm on the specified motors
    public void goForward(double cm, DcMotor... motors) {

        // Establish the HashMaps we will use to invoke the goDistanceMap function
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();

        // For each motor that was specified by the caller...
        for (DcMotor motor : motors) {
            // Put the power and the centimeters needed into the respective HashMaps
            powers.put(motor, 1f);
            cms.put(motor, cm);
        }

        // Finally, call the goDistanceMap function with those HashMaps
        goDistanceMap(powers, cms);
    }

    public void zeroTurnMod(DcMotor[] lefts, DcMotor[] rights, double angle) {

        // Establish the HashMaps we will use to invoke the goDistanceMap function
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();

        // Set up the powers with equal and opposite for zero-turning affect
        for (DcMotor left : lefts) {
            powers.put(left, (float) (angle < 0 ? 1 : -1));
            cms.put(left, angleToDistance(Math.abs(angle)));
        }
        for (DcMotor right : rights) {
            powers.put(right, (float) (angle < 0 ? -1 : 1));
            cms.put(right, angleToDistance(Math.abs(angle)));
        }

        // Convert the angles into a distance in centimeters to use

        // Invoke the goDistanceMap function
        goDistanceMap(powers, cms);
    }

    public void zeroTurn(DcMotor left, DcMotor right, double angle) {
        zeroTurnMod(new DcMotor[]{left}, new DcMotor[]{right}, angle);
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
        boolean active = true;
        while (active && !starts.isEmpty()) {
            active = !(OP_MODE instanceof LinearOpMode) || ((LinearOpMode) OP_MODE).opModeIsActive();
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

    public void runUntilEncoder(DcMotor motor, float encoderTicks){
        if (motor.getCurrentPosition() != encoderTicks) {
            boolean dir = motor.getCurrentPosition() < encoderTicks;
            motor.setPower(dir ? 1 : -1);
            if (dir) {
                while (dir) {
                    dir = motor.getCurrentPosition() < encoderTicks;
                    OP_MODE.telemetry.addData("RUNUNTIL", motor.getCurrentPosition());
                    OP_MODE.telemetry.update();
                }
            } else {
                while (!dir) {
                    dir = motor.getCurrentPosition() < encoderTicks;
                    OP_MODE.telemetry.addData("RUNUNTIL", motor.getCurrentPosition());
                    OP_MODE.telemetry.update();
                }
            }
            motor.setPower(0);
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
