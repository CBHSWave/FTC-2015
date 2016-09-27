package com.qualcomm.ftcrobotcontroller.opmodes.wave2016;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Wes on 9/27/16.
 */
public class WaveManual extends WaveJaggums {
    protected DcMotorController valkyrie;

    protected DcMotor testMotor;

    @Override
    public void setup() {
        valkyrie = getPart("valkyrie", hardwareMap.dcMotorController);
        valkyrie.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        hardwareNames.put(valkyrie, "valkyrie");

        testMotor = new DcMotor(valkyrie, 1);
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
