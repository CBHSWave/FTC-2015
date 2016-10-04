package org.firstinspires.ftc.teamcode.wave;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Wes on 9/13/16.
 */
public class Encoders {
//    public static HashMap<DcMotor, Double> startValue = new HashMap<DcMotor, Integer>();

    public static void register(DcMotor motor, double time) {
//        startValue.put(motor,  Math.abs(motor.getCurrentPosition()));
    }

    public static double pos(DcMotor motor) {
        return Math.abs(motor.getCurrentPosition());
    }

    public static double rotationsSince(DcMotor motor, double time) {
//        return startValue.get(motor);
        return 0;
    }
}
