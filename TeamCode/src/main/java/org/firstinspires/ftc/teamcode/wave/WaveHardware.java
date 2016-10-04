package org.firstinspires.ftc.teamcode.wave;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.*;

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
        hardwareNames.put(wheelController, "wheels");

        motorLeft = new DcMotorImpl(wheelController, 1);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(motorLeft, "motorLeft");

        motorRight = new DcMotorImpl(wheelController, 2);
        hardwareNames.put(motorRight, "motorRight");
    }

    protected <A extends HardwareDevice> A getPart(String name, HardwareMap.DeviceMapping<A> mapping) {
        try {
            return mapping.get(name);
        } catch (IllegalArgumentException a) {
            telemetry.addData("Bad part name:", a.getLocalizedMessage());
            DbgLog.error(a.getLocalizedMessage());
        }
        return null;
    }
}
