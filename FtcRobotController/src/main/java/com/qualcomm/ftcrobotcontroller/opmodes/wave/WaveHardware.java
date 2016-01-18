package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.*;

import java.util.HashMap;

/**
 * Created by Wes
 * forms hardware hooks to use later
 */
public abstract class WaveHardware extends Wave {
    protected HashMap<HardwareDevice, String> hardwareNames = new HashMap<HardwareDevice, String>();

    protected DcMotorController wheelController;
    protected DcMotor motorRight;
    protected DcMotor motorLeft;

    protected DcMotorController liftController;
    protected DcMotor liftMotor1;
    protected DcMotor liftMotor2;

    protected DcMotorController spinController;
    protected DcMotor spinnerMotor;
    protected DcMotor churroGrabber;

    protected ServoController servoController;
    protected Servo bucketRotationServo;


    @Override
    protected void setup() {
        // Drive Motors

        wheelController = getPart("wheelController", hardwareMap.dcMotorController);
        wheelController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        hardwareNames.put(wheelController, "wheelController");

        motorLeft = new DcMotor(wheelController, 1);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(motorLeft, "motorLeft");

        motorRight = new DcMotor(wheelController, 2);
        hardwareNames.put(motorRight, "motorRight");

        // Spinner Motors

        spinController = getPart("spinController", hardwareMap.dcMotorController);
        spinController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        hardwareNames.put(spinController, "spinController");

        spinnerMotor = new DcMotor(spinController, 1);
        spinnerMotor.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(spinnerMotor, "spinnerMotor");

        churroGrabber = new DcMotor(spinController, 2);
        hardwareNames.put(churroGrabber, "churroGrabber");

        // Lift Motors

        liftController = getPart("liftController", hardwareMap.dcMotorController);
        liftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        hardwareNames.put(liftController, "liftController");

        liftMotor1 = new DcMotor(liftController, 1);
        liftMotor1.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(liftMotor1, "liftMotor1");

        liftMotor2 = new DcMotor(liftController, 2);
        hardwareNames.put(liftMotor2, "liftMotor2");

        // Servos

        servoController = getPart("servoController", hardwareMap.servoController);
        servoController.pwmEnable();
        hardwareNames.put(servoController, "servoController");

        bucketRotationServo = new Servo(servoController, 1);
        hardwareNames.put(bucketRotationServo, "bucketRotationServo");
        bucketRotationServo.setPosition(0.5);
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
