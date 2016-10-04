package org.firstinspires.ftc.teamcode.wave2016;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

/**
 * Created by Wes on 9/27/16.
 */
public class WaveManual extends WaveJaggums {
    protected DcMotorController valkyrie;

    protected DcMotor testMotor;

    @Override
    public void setup() {
        valkyrie = getPart("valkyrie", hardwareMap.dcMotorController);
        hardwareNames.put(valkyrie, "valkyrie");

        testMotor = new DcMotorImpl(valkyrie, 1);
    }

    @Override
    public void loop() {
        if (gamepad1.b) {
            setMotors(1, testMotor);
        } else if (gamepad1.x){
            setMotors(-1, testMotor);
        } else {
            setMotors(0, testMotor);
        }
        super.loop();
    }
}
