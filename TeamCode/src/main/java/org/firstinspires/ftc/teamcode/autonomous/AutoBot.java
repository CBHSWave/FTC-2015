package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.general.Call;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;

import java.util.concurrent.TimeUnit;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="AutoBot", group="Auto")
public class AutoBot extends Auto {

    @Override
    public void runOpMode() throws InterruptedException {
        robot.mecanum();
        robot.knock();

        waitForStart();

        robot.knock.setPosition(0);
        sleep(500);
        robot.knock.setPosition(0.5);
        sleep(500);
    }
}

