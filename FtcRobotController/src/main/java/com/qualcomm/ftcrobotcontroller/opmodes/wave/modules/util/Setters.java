package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Setters implements Module {
    protected Wave wave;

    public Setters(Wave wave) {
        this.wave = wave;
    }

    @Override
    public void setup(Wave mode) {}

    @Override
    public void loop(Wave mode) {
        wave = mode;
        mode.telemetry.addData("Text", "*** Robot Data***");
    }

    @Override
    public void stop(Wave mode) {

    }

    public void motors(double power, DcMotor... motors) {
        for (DcMotor motor : motors) {
            String name = wave.hardwareNames.get(motor);
            if (name == null)
                name = "Unknown motor";
            wave.telemetry.addData(name + " tgt pwr", String.format("%.2f", power));
            motor.setPower(power);
        }
    }

    public void servosDirection(double position, Servo.Direction direction, Servo... servos) {
        for (Servo servo : servos) {
            String name = wave.hardwareNames.get(servo);
            if (name == null)
                name = "Unknown servo";
            wave.telemetry.addData(name + " tgt position & direction", String.format("%.2f %s", position, direction.name()));
            servo.setDirection(direction);
            servo.setPosition(position);
        }
    }

    public void servos(double position, Servo... servos) {
        for (Servo servo : servos) {
            servosDirection(position, servo.getDirection(), servo);
        }
    }
}
