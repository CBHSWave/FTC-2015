package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.HashMap;

/**
 * Created by wjackson on 9/18/2017.
 */

public class AutoUtil {
    private final double TICKS_PER_CM;
    private final double RADIUS;
    private final LinearOpMode OP_MODE;
    private final double CIRC;

    public AutoUtil(double TICKS_PER_CM, double RADIUS, LinearOpMode OP_MODE) {
        this.TICKS_PER_CM = TICKS_PER_CM;
        this.RADIUS = RADIUS;
        this.OP_MODE = OP_MODE;
        this.CIRC = 2 * Math.PI * this.RADIUS;
    }

    public void goForward(DcMotor[] motors, double cm) {
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();
        for (DcMotor motor : motors) {
            powers.put(motor, Float.valueOf(1));
            cms.put(motor, cm);
        }
        goDistanceMap(powers, cms);
    }

    public void zeroTurn(DcMotor left, DcMotor right, double angle) {
        HashMap<DcMotor, Float> powers = new HashMap<>();
        powers.put(left, Float.valueOf(angle < 0 ? 1 : -1));
        powers.put(right, Float.valueOf(angle < 0 ? -1 : 1));
        HashMap<DcMotor, Double> cms = new HashMap<>();
        cms.put(left, angleToDistance(Math.abs(angle)));
        cms.put(right, angleToDistance(Math.abs(angle)));
        goDistanceMap(powers, cms);
    }

    public void goDistanceMap(HashMap<DcMotor, Float> motors, HashMap<DcMotor, Double> cm) {
        HashMap<DcMotor, Double> hashMap = new HashMap<>();
        for (DcMotor motor : motors.keySet()) {
            motor.setPower(motors.get(motor));
            hashMap.put(motor, traveled(motor));
        }
        while (OP_MODE.opModeIsActive() && !hashMap.isEmpty()) {
            for (DcMotor motor : motors.keySet()) {
                if (hashMap.containsKey(motor) && Math.abs(traveled(motor) - hashMap.get(motor)) > cm.get(motor)) {
                    motor.setPower(0);
                    hashMap.remove(motor);
                }
            }
        }
    }

    // Return centimeters traveled since start
    public double traveled(DcMotor motor) {
        return convertEncoder(motor.getCurrentPosition());
    }

    //TODO
    public double convertEncoder(float encoderTicks) {
        return encoderTicks / TICKS_PER_CM;
    }

    public double angleToDistance(double angle) {
        return angle / 360 * CIRC;
    }
}
