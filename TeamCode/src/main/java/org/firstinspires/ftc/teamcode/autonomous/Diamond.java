package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;

/**
 * Created by wjackson on 9/18/2018.
 */

@Autonomous(name="Diamond", group="Auto")
public class Diamond extends Auto {

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new HardwareBot();   // Use our hardware
        robot.mecanum(hardwareMap);

        waitForStart();

        DcMotor[] motors = new DcMotor[]{robot.fl, robot.fr, robot.bl, robot.br};
        double[] northeast = GeneralUtil.polarMecanum(45, 1);
        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);

        AutoUtil.setMotors(northeast, motors);
        sleep(1000);
        AutoUtil.setMotors(southeast, motors);
        sleep(1000);
        AutoUtil.setMotors(southwest, motors);
        sleep(1000);
        AutoUtil.setMotors(northwest, motors);
        sleep(1000);
        AutoUtil.setMotors(0, motors);
    }
}
