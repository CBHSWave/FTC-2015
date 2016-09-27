package com.qualcomm.ftcrobotcontroller.opmodes.wave2016;

/**
 * Created by Wes on 9/13/16.
 */
public class TestAuto extends TestHard {
    public void loop() {
        motorLeft.setPower(0.1);
        telemetry.addData("meow", motorLeft.getCurrentPosition());
    }
}
