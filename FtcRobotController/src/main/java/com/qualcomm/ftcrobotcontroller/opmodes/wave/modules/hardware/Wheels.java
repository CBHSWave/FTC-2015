package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Wheels implements Module<Wave> {
    protected DcMotorController wheelController;
    public DcMotor motorRight;
    public DcMotor motorLeft;

    @Override
    public void setup(Wave mode) {
        wheelController = mode.getPart("wheelController", mode.hardwareMap.dcMotorController, mode.telemetry);
        wheelController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);

        motorLeft = new DcMotor(wheelController, 1);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        mode.hardwareNames.put(motorLeft, "motorLeft");

        motorRight = new DcMotor(wheelController, 2);
    }

    @Override
    public void loop(Wave mode) {

    }
}
