package org.firstinspires.ftc.teamcode.wave;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import java.util.HashMap;

/**
 * Created by Wes
 * Top Level class for all bot stuff, automatically loops
 */
public abstract class Wave extends OpMode {
    protected HashMap<HardwareDevice, String> hardwareNames = new HashMap<HardwareDevice, String>();
    protected Gamepad lastGamepad1 = new Gamepad();
    protected Gamepad lastGamepad2 = new Gamepad();

    protected double startTime;

    /**
     * Use to setup everything before entering loop
     */
    protected abstract void setup();

    @Override
    public void start() {
        startTime = time;
    }

    @Override
    public void init() {
        setup();
    }

    @Override
    public void loop() {
        try {
            lastGamepad1.copy(gamepad1);
            lastGamepad2.copy(gamepad2);
        } catch (RobotCoreException e) {
            telemetry.addData("Copy failure", "Gamepad copy failed");
        }
    }
}
