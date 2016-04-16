package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.google.inject.Injector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Wes
 * To be extended by custom opmodes, provides telemetry logging
 */
public abstract class WaveTelemetry extends Wave {
    public WaveTelemetry(Injector injector) {
        super(injector);
    }

    @Override
    public void loop() {
        telemetry.addData("Text", "*** Robot Data***");
        super.loop();
    }

    public void setMotors(double power, DcMotor... motors) {
        for (DcMotor motor : motors) {
            String name = hardwareNames.get(motor);
            if (name == null)
                name = "Unknown motor";
            telemetry.addData(name + " tgt pwr", String.format("%.2f", power));
            motor.setPower(power);
        }
    }

    public void setServosDirection(double position, Servo.Direction direction, Servo... servos) {
        for (Servo servo : servos) {
            String name = hardwareNames.get(servo);
            if (name == null)
                name = "Unknown servo";
            telemetry.addData(name + " tgt position & direction", String.format("%.2f %s", position, direction.name()));
            servo.setDirection(direction);
            servo.setPosition(position);
        }
    }

    public void setServos(double position, Servo... servos) {
        for (Servo servo : servos) {
            setServosDirection(position, servo.getDirection(), servo);
        }
    }
}
