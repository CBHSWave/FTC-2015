package org.firstinspires.ftc.teamcode.wave2016;

import org.firstinspires.ftc.teamcode.wave.WaveTele;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;

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

        jaggum1 = new ServoImpl(servoController, 1);
        hardwareNames.put(jaggum1, "jaggum1");
        jaggum2 = new ServoImpl(servoController, 2);
        hardwareNames.put(jaggum2, "jaggum2");
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
