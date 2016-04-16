package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class BucketHard implements Module {
    protected ServoController servoController;
    public Servo bucketRotationServo;
    public Servo bucketDoor;

    @Override
    public void setup(Wave mode) {
        servoController = mode.getPart("servoController", mode.hardwareMap.servoController, mode.telemetry);
        servoController.pwmEnable();

        bucketRotationServo = new Servo(servoController, 1);
        bucketRotationServo.setPosition(0.5);

        bucketDoor = new Servo(servoController, 2);
        bucketDoor.setPosition(0);
    }

    @Override
    public void loop(Wave mode) {}
}
