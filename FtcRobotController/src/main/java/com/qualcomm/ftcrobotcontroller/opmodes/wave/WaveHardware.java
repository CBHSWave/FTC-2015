package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.WaveTelemetry;
import com.qualcomm.robotcore.hardware.*;

import java.util.HashMap;

/**
 * Created by Wes
 * forms hardware hooks to use later
 */
public abstract class WaveHardware extends WaveTelemetry {

    protected DcMotorController wheelController;
    protected DcMotor motorRight;
    protected DcMotor motorLeft;

    @Override
    protected void setup() {
        // Drive Motors

        wheelController = getPart("wheels", hardwareMap.dcMotorController);
        wheelController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        hardwareNames.put(wheelController, "wheels");

        motorLeft = new DcMotor(wheelController, 1);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(motorLeft, "motorLeft");

        motorRight = new DcMotor(wheelController, 2);
        hardwareNames.put(motorRight, "motorRight");
    }

    protected <A> A getPart(String name, HardwareMap.DeviceMapping<A> mapping) {
        try {
            return mapping.get(name);
        } catch (IllegalArgumentException a) {
            telemetry.addData("Bad part name:", a.getLocalizedMessage());
            DbgLog.error(a.getLocalizedMessage());
        }
        return null;
    }
}
