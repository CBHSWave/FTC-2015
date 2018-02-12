package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareBot;

import java.util.concurrent.TimeUnit;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="AutoBot", group="Auto")
public class AutoBot extends OpMode {

    /* Declare OpMode members. */

    HardwareBot robot;
    boolean done = false;

    @Override
    public void init(){
        robot = new HardwareBot();   // Use our hardware
        robot.mecanum(hardwareMap);
    }

    @Override
    public void loop() {
        if (done) {
            return;
        }

        robot.fl.setPower(-0.5);
        robot.br.setPower(-0.5);
        robot.fr.setPower(0.5);
        robot.bl.setPower(0.5);

        robot.period.reset();
        while (robot.period.time(TimeUnit.MILLISECONDS) < 1000) {
        }

        robot.fl.setPower(0);
        robot.br.setPower(0);
        robot.fr.setPower(0);
        robot.bl.setPower(0);
        robot.period.reset();
        done = true;
    }
}

