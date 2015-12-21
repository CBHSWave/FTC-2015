package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Optional;

/**
 * Created by Wes forms hardware hooks to use later
 */
public class BotHardware extends OpMode {
    protected DcMotor tankMotorLeft;
    protected DcMotor tankMotorRight;

    @Override
    public void init() {
        tankMotorLeft = getPart("tank_motor_left", hardwareMap.dcMotor);
        tankMotorLeft.setDirection(DcMotor.Direction.FORWARD);
        tankMotorRight = getPart("tank_motor_right", hardwareMap.dcMotor);
        tankMotorRight.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        // TODO
    }

    protected <A> A getPart(String name, HardwareMap.DeviceMapping<A> mapping) {
        try {
            return mapping.get(name);
        } catch (IllegalArgumentException a) {
            DbgLog.msg(a.getLocalizedMessage());
        }
        return null;
    }
}
