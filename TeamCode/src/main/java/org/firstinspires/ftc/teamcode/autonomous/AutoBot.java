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
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);

        robot.mecanum();

        robot.winch();

        robot.knock();
    }

    @Override
    public void run() throws InterruptedException {
        DcMotor[] motors = GeneralUtil.optArray(robot.fl, robot.fr, robot.bl, robot.br);


        double[] northeast = GeneralUtil.polarMecanum(45, 1);
        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);
        double[] north = GeneralUtil.polarMecanum(0, 1);
        double[] south = GeneralUtil.polarMecanum(180, 1);
        double[] east = GeneralUtil.polarMecanum(90, 1);
        double[] west = GeneralUtil.polarMecanum(270, 1);

        robot.winch.ifPresent(winch -> {
            winch.setPower(-0.2);
            sleep(100);
            winch.setPower(0.2);
            sleep(100);
            winch.setPower(0);
        });

        AutoUtil.setMotors(east, motors);
        sleep(100);
        AutoUtil.stopMotors(motors);
    }
}

