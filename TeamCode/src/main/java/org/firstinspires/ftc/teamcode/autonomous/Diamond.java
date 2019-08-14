package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.manual.HardwareBot;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;

/**
 * Created by wjackson on 9/18/2018.
 */

@Autonomous(name="Diamond", group="Auto")
public class Diamond extends Auto {

    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);
        robot.mecanum();
    }

    @Override
    public void run() throws InterruptedException {
        DcMotor[] motors = GeneralUtil.optArray(robot.fl, robot.fr, robot.bl, robot.br);

        double[] northeast = GeneralUtil.polarMecanum(45, 1);
        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);

        AutoUtil.setMotors(northeast, motors);
        sleep(1000);

        AutoUtil.stopMotors(motors);
        sleep(100);

        AutoUtil.setMotors(southeast, motors);
        sleep(1000);

        AutoUtil.stopMotors(motors);
        sleep(100);

        AutoUtil.setMotors(southwest, motors);
        sleep(1000);

        AutoUtil.stopMotors(motors);
        sleep(100);

        AutoUtil.setMotors(northwest, motors);
        sleep(1000);


        // Final Stopping, called on everything
        AutoUtil.stopMotors((DcMotor[]) robot.motors.toArray());
    }
}
