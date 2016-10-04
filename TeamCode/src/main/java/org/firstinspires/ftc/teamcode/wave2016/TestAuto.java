package org.firstinspires.ftc.teamcode.wave2016;

/**
 * Created by Wes on 9/13/16.
 */
public class TestAuto extends TestHard {
    public void loop() {
        setMotors(0.1, motorLeft);
        telemetry.addData("meow", motorLeft.getCurrentPosition());
    }
}
