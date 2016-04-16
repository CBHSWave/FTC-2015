package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.hardware;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class SpinnerHard implements Module {
    public DcMotorController spinController;
    public DcMotor spinnerMotor;
    public DcMotor churroGrabber;
    public DcMotor wireMotor;
    
    @Override
    public void setup(Wave mode) {
        spinController = mode.getPart("spinController", mode.hardwareMap.dcMotorController, mode.telemetry);
        spinController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        mode.hardwareNames.put(spinController, "spinController");

        spinnerMotor = new DcMotor(spinController, 1);
        spinnerMotor.setDirection(DcMotor.Direction.REVERSE);
        mode.hardwareNames.put(spinnerMotor, "spinnerMotor");

        // OPTIONAL CHURRO
        churroGrabber = new DcMotor(spinController, 2);
        mode.hardwareNames.put(churroGrabber, "churroGrabber");

        wireMotor = new DcMotor(spinController, 2);
        mode.hardwareNames.put(wireMotor, "wireMotor");
    }

    @Override
    public void loop(Wave mode) {}

    @Override
    public void stop(Wave mode) {

    }
}
