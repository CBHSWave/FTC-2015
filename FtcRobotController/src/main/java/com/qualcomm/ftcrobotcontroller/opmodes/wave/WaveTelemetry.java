package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Wes
 * To be extended by custom opmodes, provides telemetry logging
 */
public abstract class WaveTelemetry extends Wave {
    @Override
    public void loop() {
        telemetry.addData("Text", "*** Robot Data***");
    }

    protected void setMotors(double power, DcMotor... motors) {
        for (DcMotor motor : motors) {
            String name = hardwareNames.get(motor);
            if (name == null)
                name = "Unknown motor";
            telemetry.addData(name + " tgt pwr", String.format("%.2f", power));
            motor.setPower(power);
        }
    }

    protected void setServosDirection(double position, Servo.Direction direction, Servo... servos) {
        for (Servo servo : servos) {
            String name = hardwareNames.get(servo);
            if (name == null)
                name = "Unknown servo";
            telemetry.addData(name + " tgt position & direction", String.format("%.2f %s", position, direction.name()));
            servo.setDirection(direction);
            servo.setPosition(position);
        }
    }

    protected void setServos(double position, Servo... servos) {
        for (Servo servo : servos) {
            setServosDirection(position, servo.getDirection(), servo);
        }
    }
}
