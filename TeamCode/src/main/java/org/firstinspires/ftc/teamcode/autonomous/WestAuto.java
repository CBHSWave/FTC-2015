package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="WestAuto", group="Auto")
public class WestAuto extends Auto {

    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);

        robot.mecanum();

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


        AutoUtil.setMotors(west , motors);
        sleep(1000);
        AutoUtil.stopMotors(motors);
        sleep(100);

        AutoUtil.setMotors(north , motors);
        sleep(1000);
        AutoUtil.stopMotors(motors);
        sleep(100);

        robot.knock.ifPresent(knock -> {
            knock.setPosition(1.0);
            sleep(50);
            knock.setPosition(0.0);

        });

        AutoUtil.setMotors(south , motors);
        sleep(2000);
        AutoUtil.stopMotors(motors);

    }
}

