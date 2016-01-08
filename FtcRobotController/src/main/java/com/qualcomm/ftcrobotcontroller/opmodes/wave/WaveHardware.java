package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Wes
 * forms hardware hooks to use later
 */
public abstract class WaveHardware extends Wave {
    protected DcMotor motorRight;
    protected DcMotor motorLeft;
    protected DcMotorController motorController;

    @Override
    protected void setup() {
        motorController = getPart("motorController", hardwareMap.dcMotorController);
        motorController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        motorRight = new DcMotor(motorController, 1);
        motorLeft = new DcMotor(motorController, 2);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    protected <A> A getPart(String name, HardwareMap.DeviceMapping<A> mapping) {
        try {
            return mapping.get(name);
        } catch (IllegalArgumentException a) {
            DbgLog.error(a.getLocalizedMessage());
        }
        return null;
    }
}
