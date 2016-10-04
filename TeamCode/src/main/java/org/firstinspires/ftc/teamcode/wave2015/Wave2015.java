package org.firstinspires.ftc.teamcode.wave2015;

import org.firstinspires.ftc.teamcode.wave.WaveTele;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;

/**
 * Created by Wes on 9/13/16.
 */
public class Wave2015 extends WaveTele {
    protected DcMotorController liftController;
    protected DcMotor liftMotor1;
    protected DcMotor liftMotor2;

    protected DcMotorController spinController;
    protected DcMotor spinnerMotor;
    protected DcMotor churroGrabber;
    protected DcMotor wireMotor;

    protected ServoController servoController;
    protected Servo bucketRotationServo;
    protected Servo bucketDoor;

    public void setup() {
        // Spinner Motors

        spinController = getPart("spinController", hardwareMap.dcMotorController);
        hardwareNames.put(spinController, "spinController");

        spinnerMotor = new DcMotorImpl(spinController, 1);
        spinnerMotor.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(spinnerMotor, "spinnerMotor");

        // OPTIONAL CHURRO
        churroGrabber = new DcMotorImpl(spinController, 2);
        hardwareNames.put(churroGrabber, "churroGrabber");

        wireMotor = new DcMotorImpl(spinController, 2);
        hardwareNames.put(wireMotor, "wireMotor");

        // Lift Motors

        liftController = getPart("liftController", hardwareMap.dcMotorController);
        hardwareNames.put(liftController, "liftController");

        liftMotor1 = new DcMotorImpl(liftController, 1);
        liftMotor1.setDirection(DcMotor.Direction.REVERSE);
        hardwareNames.put(liftMotor1, "liftMotor1");

        liftMotor2 = new DcMotorImpl(liftController, 2);
        hardwareNames.put(liftMotor2, "liftMotor2");

        // Servos

        servoController = getPart("servoController", hardwareMap.servoController);
        servoController.pwmEnable();
        hardwareNames.put(servoController, "servoController");

        bucketRotationServo = new ServoImpl(servoController, 1);
        hardwareNames.put(bucketRotationServo, "bucketRotationServo");
        bucketRotationServo.setPosition(0.5);

        bucketDoor = new ServoImpl(servoController, 2);
        hardwareNames.put(bucketDoor, "bucketDoor");
        bucketDoor.setPosition(0);
    }
}
