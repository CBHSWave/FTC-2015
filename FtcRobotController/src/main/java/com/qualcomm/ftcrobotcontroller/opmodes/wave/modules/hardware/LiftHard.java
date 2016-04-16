package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.util.HardwareUtil;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class LiftHard implements Module {
    protected DcMotorController liftController;
    public DcMotor liftMotor1;
    public DcMotor liftMotor2;

    @Override
    public void setup(Wave mode) {
        liftController = mode.getPart("liftController", mode.hardwareMap.dcMotorController, mode.telemetry);
        liftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);

        liftMotor1 = new DcMotor(liftController, 1);
        liftMotor1.setDirection(DcMotor.Direction.REVERSE);

        liftMotor2 = new DcMotor(liftController, 2);
    }

    @Override
    public void loop(Wave mode) {}
}
