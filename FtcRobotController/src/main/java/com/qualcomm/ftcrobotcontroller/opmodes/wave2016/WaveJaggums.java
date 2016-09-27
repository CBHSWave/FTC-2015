package com.qualcomm.ftcrobotcontroller.opmodes.wave2016;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTele;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

/**
 * Created by Wes on 9/27/16.
 */
public class WaveJaggums extends WaveTele {
    protected ServoController servoController;
    protected Servo jaggum1;
    protected Servo jaggum2;
    protected JaggumState jaggumState = JaggumState.CLOSED;
//    protected boolean jaggumsActed = true;

    @Override
    public void setup() {
        servoController = getPart("servos", hardwareMap.servoController);
        hardwareNames.put(servoController, "servos");

        jaggum1 = new Servo(servoController, 1);
        jaggum2 = new Servo(servoController, 2);
        super.setup();
    }

    @Override
    public void loop() {
        if (gamepad1.a && !lastGamepad1.a) {
            jaggumState = jaggumState.advance();
            jaggum1.setPosition(jaggumState.toDouble());
            jaggum2.setPosition(jaggumState.toDouble());
        }
        super.loop();
    }

    protected enum JaggumState {
        CLOSED,
        OPEN,
        FAR;

        JaggumState advance() {
            if (this == CLOSED) {
                return OPEN;
            } else if (this == OPEN) {
                return FAR;
            } else {
                return CLOSED;
            }
        }

        double toDouble() {
            if (this == CLOSED) {
                return 0;
            } else if (this == OPEN) {
                return 90;
            } else {
                return 120;
            }
        }
    }

}
